package br.com.t3mb.measure.api.model;

import java.io.Serializable;


public class UserGroup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6069862840554494646L;
	private long id;
	private String name;
	
	public UserGroup() {}

	public UserGroup(long id, String name) {
		super();
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
	
	public String toString() {
		return "(" + this.id + ") - " + this.name;
	}
	
	

}
