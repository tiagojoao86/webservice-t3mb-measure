package br.com.t3mb.measure.api.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.security.JwtUserFactory;
import br.com.t3mb.measure.api.service.UserService;

public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = Optional.ofNullable(userService.getUserByLogin(username));		
		if (user.isPresent()) {
			return JwtUserFactory.create(user.get());
		}
		
		throw new UsernameNotFoundException("Usuário não encontrado");		
	}

}
