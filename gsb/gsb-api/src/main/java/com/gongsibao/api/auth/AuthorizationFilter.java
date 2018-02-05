package com.gongsibao.api.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gongsibao.api.config.DeviceException;
import com.gongsibao.api.dto.ma.MaResponseCodeEnum;
import com.gongsibao.api.util.ResponseResult;
import org.netsharp.util.StringManager;

/**
 * @author hw
 *         权限过滤
 */
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private HttpServletResponse servletResponse;

    public void filter(ContainerRequestContext requestContext) throws DeviceException {

        String token = requestContext.getHeaderString("token");
        if (!StringManager.isNullOrEmpty(token)) {
            if (!token.equals("47K41D5885D")) {
                //Response response = Response.ok("token值不对").tag("101").status(200).type(MediaType.APPLICATION_JSON).build();

                throw new DeviceException(MaResponseCodeEnum.paraError.getText(),"token值不对");

            }

            // TODO:拦截响应
        } else {
            Response response = Response.ok("token值不能为空").tag("101").status(200).type(MediaType.APPLICATION_JSON).build();
            throw new WebApplicationException(response);
            //throw new IOException("token值不能为空");
        }

//		// 到redis中获取用户信息
//		User user = Token.getUser(token);
//		if (user == null) {
//			// TODO:拦截响应
//		}
    }

}
