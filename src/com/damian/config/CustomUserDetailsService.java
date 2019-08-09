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
import com.damian.pojo.Roles;
import com.damian.pojo.Usuario;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario != null) {
			for(Roles rol: usuario.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(rol.getRol()));
			}
			User user = new User(usuario.getUsuario(), usuario.getClave(), authorities);
			return user;
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

}
