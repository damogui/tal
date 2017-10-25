package org.netsharp.communication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.netsharp.communication.proxy.LocalProxy;
import org.netsharp.communication.proxy.RmiProxy;
import org.netsharp.util.ReflectManager;

public class ServiceFactory {

    public static <T> T create(Class<?> type) {
        return create(type.getName());
    }

    public static <T> T create(String type) {

    	return create(type,null);

    }
    
    public static <T> T create(Class<?> type,CommunicationConfiguration configuration){
    	
    	return create(type.getName(),configuration);
    	
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T create(String type,CommunicationConfiguration configuration){
    	
    	ServiceManage.initialize();
    	
    	InvocationHandler proxy = null;
    	
    	if(configuration==null){
    		proxy = new LocalProxy(type);
    	}
    	else if(configuration.getTransportProtocol()==TransportProtocol.local){
    		proxy = new LocalProxy(type);
    	}
    	else if(configuration.getTransportProtocol()==TransportProtocol.rmi){
    		proxy = new RmiProxy(type);
    	}
    	else{ 
    		throw new CommunicationException("don't support transport protocol :"+configuration.getTransportProtocol());
    	}
    	
    	Class<?> interfaceType = ReflectManager.getType(type);
        Object proxyObject = Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[] {interfaceType}, proxy);

         return (T) proxyObject;
    }
}
