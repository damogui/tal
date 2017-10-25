package org.netsharp.communication.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.IServiceInvokeInterceptor;
import org.netsharp.communication.SiContext;

public class InterceptorSiContext implements IServiceInvokeInterceptor {
	
	private static final Log logger = LogFactory.getLog("service."+InterceptorSiContext.class.getName());

	SiContext current;
	
	@Override
	public void invoking(SiContext ctx) {

		this.current = SiContext.getCurrent();
		
		if (this.current == null) {
			ctx.Top = ctx;
			SiContext.setCurrent(ctx);
			
			logger.trace("{"+ctx.Name);
		} 
		else {
			this.current.getSubs().add(ctx);
			ctx.Parent = current;
			ctx.Top=current.Top;
		}
	}

	@Override
	public void invoked(SiContext ctx) {
		
		if(ctx.Top==ctx){
			logger.trace("}"+ctx.Name);
			SiContext.setCurrent(null);
		}
	}
}
