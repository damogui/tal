package org.netsharp.authorization;

import org.netsharp.core.BusinessException;

public class LoginException extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	public LoginException(){
		
		super("登录后才能访问此功能");
		
		this.setCode("0001");
	}
	
	public LoginException(String message){
		super(message);
	}
	
	public LoginException(Throwable throwable){
		super(throwable);
	}
	
	public LoginException(String message,Throwable throwable){
		super(message,throwable);
	}
}
