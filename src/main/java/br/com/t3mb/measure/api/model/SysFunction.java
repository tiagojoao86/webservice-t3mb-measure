package br.com.t3mb.measure.api.model;

import java.io.Serializable;
import java.util.List;

public class SysFunction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7728197727131775763L;
	
	private long id;
	private String name;
	private String url;
	private List<SysFunction> subFunctions;
	
	public SysFunction() {}

	public SysFunction(long id, String name, String url, List<SysFunction> subFunctions) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.subFunctions = subFunctions;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public List<SysFunction> getSubFunctions() {
		return subFunctions;
	}

	public void setSubFunctions(List<SysFunction> subFunctions) {
		this.subFunctions = subFunctions;
	}
	
	public String toString() {
		return "(" + this.id + ") - " + this.name;
	}

}
