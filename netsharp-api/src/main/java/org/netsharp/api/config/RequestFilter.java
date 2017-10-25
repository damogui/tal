package org.netsharp.api.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class RequestFilter implements ContainerRequestFilter {

	public void filter(ContainerRequestContext containerRequestContext) throws IOException {

		String method = containerRequestContext.getMethod().toLowerCase();
		if(method.equals("options")){

            return;
        }
	}
}
