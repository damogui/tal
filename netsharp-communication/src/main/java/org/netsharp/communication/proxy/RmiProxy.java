package org.netsharp.communication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.Naming;

import org.netsharp.communication.CommunicationConfiguration;
import org.netsharp.communication.rmi.IRmiService;
import org.netsharp.communication.rmi.Message;

public class RmiProxy implements InvocationHandler {

    private String interfaceType;

    public RmiProxy(String type){
        this.interfaceType = type;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        CommunicationConfiguration configuration = CommunicationConfiguration.getConfiguration();
		
        String url ="rmi://"+configuration.getAddress()+":"+configuration.getPort()+"/rmiservice";
        
        System.out.println(url);

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

        IRmiService rmiService = (IRmiService) Naming.lookup(url);

        message = rmiService.invoke( message );

        return message.getReturnObject();
    }
}