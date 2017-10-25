package org.netsharp.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmiService extends Remote {
	
	Message invoke(Message message) throws RemoteException, Throwable;
}
