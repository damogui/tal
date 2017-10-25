package org.netsharp.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.netsharp.communication.server.ServiceDispatch;

public class RmiService extends UnicastRemoteObject implements IRmiService {

	public RmiService() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public Message invoke(Message message) throws Throwable  {

		ServiceDispatch dispatch =new ServiceDispatch();
		
		Object ret = dispatch.invoke(message);
		
		message.setPars(null);
		message.setReturnObject(ret);

		return message;
	}
}
