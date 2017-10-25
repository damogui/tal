package org.netsharp.communication.interceptor;

import java.util.Date;

import org.netsharp.communication.IServiceInvokeInterceptor;
import org.netsharp.communication.SiContext;

/**
 * 时间跟踪
 * @author xufb
 */
public class InterceptorTimeWatch implements IServiceInvokeInterceptor {

	Date startTime;
	
	@Override
	public void invoking(SiContext ctx) {
		
		this.startTime=new Date();
	}

	@Override
	public void invoked(SiContext ctx) {
		
	}
}
