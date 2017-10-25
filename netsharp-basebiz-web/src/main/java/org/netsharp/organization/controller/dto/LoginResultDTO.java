package org.netsharp.organization.controller.dto;

public class LoginResultDTO {

	private int result = 0;
	
	private Object data;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
