package br.com.t3mb.measure.api.utils;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	
	private T data;
	private List<String> errors;
	
	public Response() {}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		
		return errors;
	}	

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String error) {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		this.errors.add(error);
	}
	
	public void clearErrors() {
		this.errors = new ArrayList<String>();
	}
}
