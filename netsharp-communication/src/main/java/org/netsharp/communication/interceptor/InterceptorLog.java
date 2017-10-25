package org.netsharp.communication.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.IServiceInvokeInterceptor;
import org.netsharp.communication.SiContext;
import org.netsharp.util.StringManager;

public class InterceptorLog implements IServiceInvokeInterceptor {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private String methodName = null;

    @Override
    public void invoking(SiContext ctx) {

        this.methodName = ctx.InterfaceType.getName() + "." + ctx.Method.getName() + "()";

        logger.trace("服务开始{：" + this.methodName);
    }

    @Override
    public void invoked(SiContext ctx) {

        String message = this.methodName + "结束,";

        logger.trace("服务结束}：" + this.methodName);

        if (!ctx.IsSucceed) {
            message += "执行失败！" + StringManager.NewLine;
            
            Throwable cause = ctx.Exception.getCause();
            
            if(cause != null){
            	message += cause.toString();
            }

            // 主动抛出的业务异常不记录详情
            if (ctx.Exception instanceof org.netsharp.core.BusinessException) {
                logger.error(message);
            } else {
                logger.error(message, ctx.Exception);
            }
        }
    }
}
