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
import com.damian.pojo.UsuarioRoles;

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
		
		if(usuario != null) {
			for(UsuarioRoles usuarioRoles: usuario.getUsuarioRoles()) {				
				authorities.add(new SimpleGrantedAuthority(usuarioRoles.getPk().getRoles().getRol()));
			}
			User user = new User(usuario.getUsuario(), usuario.getClave(), authorities);
			return user;
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

}
