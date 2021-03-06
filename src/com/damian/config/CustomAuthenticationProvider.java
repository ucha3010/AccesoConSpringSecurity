package com.damian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// Datos del formulario de login
		String principal = authentication.getName();
		String credentials = (String) authentication.getCredentials();

		User user = (User) customUserDetailsService.loadUserByUsername(principal);

		if (user != null) {

			// Comprobaci�n de contrase�a
			if (passwordEncoder.matches(credentials, user.getPassword())) {
				if (!user.isEnabled()) {
					throw new DisabledException("user.disabled");
				}
				// el token persiste mientras la sesi�n del usuario exista en la sesi�n
				return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), user.getAuthorities());
			} else {
				throw new BadCredentialsException("label.Invalid.credentials");
			}
		} else {
			throw new BadCredentialsException("label.Invalid.credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
