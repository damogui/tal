package com.gongsibao.api.auth;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthExceptionMapper implements ExceptionMapper<AuthorizationException> {

	public Response toResponse(AuthorizationException exception) {
		
		
		return Response.ok(exception.getResponse()).build();
	}

}
