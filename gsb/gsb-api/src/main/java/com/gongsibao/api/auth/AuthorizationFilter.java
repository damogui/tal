package com.gongsibao.api.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.netsharp.util.StringManager;

/**
 * @author hw
 * 权限过滤
 */
public class AuthorizationFilter implements ContainerRequestFilter {

	public void filter(ContainerRequestContext requestContext) throws IOException {

		String token = requestContext.getHeaderString("token");
		if (StringManager.isNullOrEmpty(token)) {

			// TODO:拦截响应
		}

//		// 到redis中获取用户信息
//		User user = Token.getUser(token);
//		if (user == null) {
//			// TODO:拦截响应
//		}
	}

}
