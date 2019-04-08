package br.com.t3mb.measure.api.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4126885149805033673L;
	
	private long id;
	private String name;
	private String login;
	private String password;
	private List<Role> roles;
	private UserGroup userGroup;
	private User superior;
	private boolean hasSuperior;
	private String status;
	
	public User () {}

	public User(long id, String name, String login, String password, List<Role> roles, UserGroup userGroup,
			User superior, boolean hasSuperior, String status) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.roles = roles;
		this.userGroup = userGroup;
		this.superior = superior;
		this.hasSuperior = hasSuperior;
		this.status = status;
	}

	public User(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public User getSuperior() {
		return superior;
	}

	public void setSuperior(User superior) {
		this.superior = superior;
	}

	public boolean isHasSuperior() {
		return hasSuperior;
	}

	public void setHasSuperior(boolean hasSuperior) {
		this.hasSuperior = hasSuperior;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return "("+ this.id +") - "+ this.name;
	}
	
	
	
	

}
