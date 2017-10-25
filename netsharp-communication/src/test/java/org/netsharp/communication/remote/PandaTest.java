//package org.netsharp.communication.remote;
//
//import java.net.InetAddress;
//import java.net.MalformedURLException;
//import java.net.UnknownHostException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//
//import org.junit.Test;
//import org.netsharp.communication.CommunicationConfiguration;
//import org.netsharp.communication.ServiceFactory;
//
//public class PandaTest {
//
//	
//	
//    @Test
//    public void rimClientbyWorkspace(){
//    	
//    	System.out.println(CommunicationConfiguration.getConfiguration().toString());
//    	
//        IPWorkspaceService workspaceService = ServiceFactory.create(IPWorkspaceService.class,CommunicationConfiguration.getConfiguration());
//
//        String url ="/operationType/form";
//        
//        for(int i=0;i<20;i++){
//        	
//        	System.out.println(i+"---------------------------");
//        	
//        	PWorkspace workspace = workspaceService.byUrl(url);
//            
//            System.out.println(workspace.getName());
//            
//            for(PPart part : workspace.getParts()){
//            	System.out.println("url:"+part.getUrl());
//            	System.out.println("toolbar:"+part.getToolbar());
//            	System.out.println(part.getCreateTime());
//            }
//        }
//        
//    }
//    
//    @Test
//    public void unbind() throws RemoteException, MalformedURLException, NotBoundException{
//    	
//    	CommunicationConfiguration configuration = CommunicationConfiguration.getConfiguration();
//    	
//    	Registry registry = LocateRegistry.getRegistry(configuration.getPort());
//    	
//    	System.out.println(registry);
//    	
////    	UnicastRemoteObject.unexportObject(registry, true);
////    	registry.
//		
//		String url ="rmi://"+configuration.getAddress()+":"+configuration.getPort()+"/rmiservice";
//		Naming.unbind(url);
//    }
//    
//    @Test
//    public void getHostName() throws UnknownHostException{
//    	
//    	InetAddress inet = InetAddress.getLocalHost();
//    	System.out.println("本机的ip=" + inet.getHostAddress());
//    	System.out.println("本机的域名=" + inet.getCanonicalHostName());
//
//    }
//}
