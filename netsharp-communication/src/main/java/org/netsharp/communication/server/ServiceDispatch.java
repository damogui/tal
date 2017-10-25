package org.netsharp.communication.server;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.IServiceInvokeInterceptor;
import org.netsharp.communication.ServiceManage;
import org.netsharp.communication.SiContext;
import org.netsharp.communication.interceptor.InterceptorCache;
import org.netsharp.communication.interceptor.InterceptorDbSession;
import org.netsharp.communication.interceptor.InterceptorInvoke;
import org.netsharp.communication.interceptor.InterceptorLog;
import org.netsharp.communication.interceptor.InterceptorSiContext;
import org.netsharp.communication.interceptor.InterceptorTimeWatch;
import org.netsharp.communication.rmi.Message;
import org.netsharp.util.ReflectManager;

public class ServiceDispatch {

    protected static Log logger = LogFactory.getLog(ServiceDispatch.class);
    
    private Object serviceObject;

    List<IServiceInvokeInterceptor> interceptors = new ArrayList<IServiceInvokeInterceptor>();

    public ServiceDispatch() {

        this.interceptors.add(new InterceptorSiContext());
        this.interceptors.add(new InterceptorTimeWatch());
        // 日志拦截会导致有些情况下在外部得不到需要的日志，因此不建议拦截
		this.interceptors.add(new InterceptorLog());
        this.interceptors.add(new InterceptorDbSession());
        this.interceptors.add(new InterceptorCache());
        this.interceptors.add(new InterceptorInvoke());
    }

    public Object invoke(Message message) throws Throwable{
    	
    	 Class<?> interfaceType = ReflectManager.getType(message.getType());
    	 Class<?> serviceType = ServiceManage.getServiceType(interfaceType);
    	 
    	 Class<?>[] parTypes = new Class<?>[message.getParType().length]; 
    	 for(int i=0;i<parTypes.length;i++){
    		 Class<?> parType = ReflectManager.getType(message.getParType()[i]);
    		 parTypes[i]=parType;
    	 }
    	 
    	 Method method = ReflectManager.getMethod(serviceType, message.getMethod(),parTypes);

        this.serviceObject = ReflectManager.newInstance(serviceType);

        SiContext ctx = new SiContext();
        {
            ctx.InterfaceType = interfaceType;
            ctx.ServiceObject = serviceObject;
            ctx.Method = method;
            ctx.Parameters = message.getPars();
            ctx.IsSucceed = false;
            ctx.InterfaceMethod = interfaceType.getMethod(ctx.Method.getName(), method.getParameterTypes());// 接口方法
            ctx.Name=ctx.InterfaceType.getName()+"."+ctx.Method.getName();
        }

        // 调用前拦截
        for (IServiceInvokeInterceptor interceptor : this.interceptors) {
            interceptor.invoking(ctx);
        }

        // 调用后拦截
        for(int i= this.interceptors.size()-1;i>=0;i--){
            IServiceInvokeInterceptor interceptor = this.interceptors.get(i);
            interceptor.invoked(ctx);
        }

        if (ctx.IsSucceed) {
            return ctx.ReturnObject;
        } else {

            Throwable cause = ctx.Exception.getCause();
            if(cause==null){
                throw ctx.Exception;
            }
            else{
                throw cause;
            }
        }
    }
}
