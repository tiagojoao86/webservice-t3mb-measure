package br.com.t3mb.measure.api.security.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class JwtAuthenticationDto {

	private String login;
	private String password;

	public JwtAuthenticationDto() {
	}

	@NotEmpty(message = "Email não pode ser vazio.")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [email=" + login + ", senha=" + password + "]";
	}

}
