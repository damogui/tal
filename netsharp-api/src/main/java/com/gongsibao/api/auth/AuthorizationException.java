package com.gongsibao.api.auth;

public class AuthorizationException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8891849680521321298L;
	//private String response = ErrorCode.NOT_AUTHED.getMsg();
    public String getResponse(){
    	
    	return "401";
        //return this.response;
    }
}
