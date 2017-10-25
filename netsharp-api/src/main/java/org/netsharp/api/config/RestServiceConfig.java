package org.netsharp.api.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.netsharp.api.auth.AuthorizationFilterFeature;
import org.netsharp.api.controller.test.CarTypeController;
import org.netsharp.api.controller.test.CouponController;
import org.netsharp.api.controller.test.GoodsController;
import org.netsharp.api.controller.test.OrderController;
import org.netsharp.api.controller.test.UserController;

@ApplicationPath("/api/*")
public class RestServiceConfig extends ResourceConfig {
	
	public RestServiceConfig() {

		//加载resources
		register(UserController.class);
		register(CarTypeController.class);
		register(CouponController.class);
		register(GoodsController.class);
		register(OrderController.class);
		
		//注册数据转换器
		register(MyJacksonJsonProvider.class);
		
		//注册日志
		register(LoggingFilter.class);

		//注册权限拦截器
		register(AuthorizationFilterFeature.class);
		
		//注册权限拦截器
		register(ResponseFilter.class);
		register(RequestFilter.class);
		
		//压缩
		//register(GzipInterceptor.class);
		//注册异常处理器
		register(ExceptionMapperSupport.class);
	}
}
