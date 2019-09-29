package com.damian.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioDAO;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioRol;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	/**
	 * Clase CustomUserDetailsService
	 */

	@Autowired
	private UsuarioDAO usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario != null) {
			for (UsuarioRol usuarioRoles : usuario.getUsuarioRol()) {
				authorities.add(new SimpleGrantedAuthority(usuarioRoles.getPk().getRol().getRol()));
			}
			// si quiero evaluar los parámetros que faltan al crear user, tengo que agregar las excepciones en CustomAuthenticationProvider
			User user = new User(usuario.getUsuario(), usuario.getClave(), usuario.isHabilitado(), true, true, true,
					authorities);
			return user;
		} else {
			throw new UsernameNotFoundException("user.not.found");
		}
	}

}
