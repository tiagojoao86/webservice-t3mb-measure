package br.com.t3mb.measure.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.t3mb.measure.api.model.Role;
import br.com.t3mb.measure.api.model.User;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionário.
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */
	public static JwtUser create(User user) {
		JwtUser jwtUser = new JwtUser(user.getId(), user.getLogin(), user.getPassword(),
				mapToGrantedAuthorities(user.getRoles()));
		return jwtUser;
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param roles
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		});
		
		return authorities;
	}

}
