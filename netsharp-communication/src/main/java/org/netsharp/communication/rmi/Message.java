package org.netsharp.communication.rmi;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String method;
	private Object[] pars;
	private String[] parType;
	private Object returnObject;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object[] getPars() {
		return pars;
	}
	public void setPars(Object[] pars) {
		this.pars = pars;
	}
	public Object getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	public String[] getParType() {
		return parType;
	}
	public void setParType(String[] parType) {
		this.parType = parType;
	}
	
	
}
