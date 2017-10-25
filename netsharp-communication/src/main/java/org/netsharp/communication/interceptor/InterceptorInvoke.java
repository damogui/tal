package org.netsharp.communication.interceptor;

import java.lang.reflect.Method;

import org.netsharp.communication.IServiceInvokeInterceptor;
import org.netsharp.communication.SiContext;

//服务方法调用
public class InterceptorInvoke implements IServiceInvokeInterceptor {
	
	public void invoking(SiContext ctx) {
		
		if(ctx.IsCached){
			return;
		}
		
		Method method = ctx.Method;
		Object serviceObject = ctx.ServiceObject;
		Object[] args = ctx.Parameters;
		
		try {
			Object ret = method.invoke(serviceObject, args);
			ctx.ReturnObject = ret;
			ctx.IsSucceed = true;
		} catch (Exception ex) {
			ctx.Exception = ex;
		}
	}

	public void invoked(SiContext ctx) {
		
	}
}
