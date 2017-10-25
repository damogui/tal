package org.netsharp.communication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.rmi.Message;
import org.netsharp.communication.server.ServiceDispatch;

/* 本地调用接口代理 */
public class LocalProxy implements InvocationHandler {

	protected static Log logger = LogFactory.getLog(LocalProxy.class);

	private String interfaceType;

	public LocalProxy(String interfaceType) {

		this.interfaceType = interfaceType;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		ServiceDispatch dispatch =new ServiceDispatch();
		
		Message message = new Message();
		{
			message.setType(this.interfaceType);
			message.setMethod(method.getName());
			message.setPars(args);
			
			String[] parTypes = new String[method.getParameterTypes().length];
			for(int i=0;i<parTypes.length;i++){
				Class<?> pt = method.getParameterTypes()[i];
				parTypes[i] = pt.getName();
			}
			
			message.setParType(parTypes);
		}
		
		Object ret = dispatch.invoke(message);

		return ret;
	}
}