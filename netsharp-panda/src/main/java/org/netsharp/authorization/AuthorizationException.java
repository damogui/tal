package org.netsharp.authorization;

import org.netsharp.core.BusinessException;

public class AuthorizationException extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	public AuthorizationException(){
		
		super("您没有权限访问此功能!");
		
		this.setCode("0002");
	}
	
	public AuthorizationException(String message){
		super(message);
	}
	
	public AuthorizationException(Throwable throwable){
		super(throwable);
	}
	
	public AuthorizationException(String message,Throwable throwable){
		super(message,throwable);
	}
}
