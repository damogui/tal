package com.gongsibao.api.config;

import javax.ws.rs.ApplicationPath;

import com.gongsibao.api.conroller.ma.SellingController;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.gongsibao.api.auth.AuthorizationFilterFeature;
import com.gongsibao.api.conroller.igirl.TmAutoSubmitController;
import com.gongsibao.api.conroller.test.CarTypeController;
import com.gongsibao.api.conroller.test.CouponController;
import com.gongsibao.api.conroller.test.GoodsController;
import com.gongsibao.api.conroller.test.UserController;

@ApplicationPath("/api/*")
public class RestServiceConfig extends ResourceConfig {
	
	public RestServiceConfig() {

		//加载resources
		register(UserController.class);
		register(CarTypeController.class);
		register(CouponController.class);
		register(GoodsController.class);
		register(TmAutoSubmitController.class);
        register(SellingController.class);


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
