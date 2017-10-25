package org.netsharp.communication;

/**
 * 服务调用拦截器
 * @author xufangbo
 */
public interface IServiceInvokeInterceptor {
	/**
	 * 拦截前置处理
	 * @param ctx 服务调用上下文
	 */
	void invoking(SiContext ctx);
	
	
    /** 拦截后置处理
     * @param ctx 服务调用上下文
     */
    void invoked(SiContext ctx);
}
