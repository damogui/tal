package com.gongsibao.api.util;

public class ApiException extends RuntimeException{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5750622088433714576L;
	
	private int code;

	public ApiException(){
		super();
	}
	
	public ApiException(String message){
		super(message);
	}
	
	public ApiException(int code, String message){
		super(message);
		this.code=code;
	}
	
	public ApiException(Throwable throwable){
		super(throwable);
	}
	
	public ApiException(String message,Throwable throwable){
		super(message,throwable);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
