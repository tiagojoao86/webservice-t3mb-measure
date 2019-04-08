package br.com.t3mb.measure.api.model;

import java.io.Serializable;
import java.util.List;
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6950680057073671020L;
	private long id;
	private List<SysFunction> sysFunctions;
	private String name;
	
	public Role() {
		
	}

	public Role(long id, List<SysFunction> sysFunctions, String name) {
		super();
		this.id = id;
		this.sysFunctions = sysFunctions;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<SysFunction> getSysFunctions() {
		return sysFunctions;
	}

	public void setSysFunctions(List<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "("+this.id+") - " + this.name;
	}

}
