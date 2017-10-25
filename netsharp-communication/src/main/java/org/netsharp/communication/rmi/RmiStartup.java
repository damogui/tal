package org.netsharp.communication.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.CommunicationConfiguration;
import org.netsharp.communication.ServiceManage;
import org.netsharp.startup.IStartup;

public class RmiStartup implements IStartup {

	private static final Log logger = LogFactory.getLog("rmi." + RmiStartup.class.getName());
	
	public boolean startCondition() {
		return false;
	}

	public void startup() {

		try {

			ServiceManage.initialize();

			CommunicationConfiguration configuration = CommunicationConfiguration.getConfiguration();
			String url = "rmi://" + configuration.getAddress() + ":" + configuration.getPort() + "/rmiservice";

			// 创建一个远程对象
			IRmiService rmiService = new RmiService();
			LocateRegistry.createRegistry(8888);
			Naming.bind(url, rmiService);

			logger.debug("bind:" + url);
			logger.debug("rmi服务监听启动成功");
			System.out.println(">>>>>INFO:远程IHello对象绑定成功！");

		} catch (RemoteException e) {
			logger.error("rmi服务监听启动失败,创建远程对象发生异常！", e);

			// throw new CommunicationException("rmi服务监听启动失败,创建远程对象发生异常！",e);
		} catch (AlreadyBoundException e) {
			logger.error("rmi服务监听启动失败,发生重复绑定对象异常！", e);
			// throw new CommunicationException("rmi服务监听启动失败,发生重复绑定对象异常！",e);
		} catch (MalformedURLException e) {
			logger.error("rmi服务监听启动失败,发生URL畸形异常！", e);
			// throw new CommunicationException("rmi服务监听启动失败,发生URL畸形异常！",e);
		} catch (Exception e) {
			logger.error("rmi服务监听启动失败,发生未知异常！", e);
			// throw new CommunicationException("rmi服务监听启动失败,发生未知异常！",e);
		}

		//
		//
		// // ServiceManage.initialize();
		//
		// CommunicationConfiguration configuration =
		// CommunicationConfiguration.getConfiguration();
		//
		// try {
		// // System.setProperty("java.rmi.server.hostname",
		// configuration.getAddress());
		//
		// String url
		// ="rmi://"+configuration.getAddress()+":"+configuration.getPort()+"/rmiservice";
		//
		// System.out.println(url);
		//
		// logger.debug(configuration.toString());
		//
		//
		// // 创建一个远程对象
		// IRmiService rmiService = new RmiService();
		//
		// // System.setSecurityManager(new java.rmi.RMISecurityManager());
		//
		// //默认端口是1099
		// LocateRegistry.getRegistry(configuration.getPort());
		//
		// Naming.bind(url, rmiService);
		// logger.debug("bind:"+url);
		//
		// logger.debug("rmi服务监听启动成功");
		//
		// } catch (RemoteException e) {
		// logger.error("rmi服务监听启动失败,创建远程对象发生异常！",e);
		//
		// throw new CommunicationException("rmi服务监听启动失败,创建远程对象发生异常！",e);
		// } catch (AlreadyBoundException e) {
		// logger.error("rmi服务监听启动失败,发生重复绑定对象异常！",e);
		// throw new CommunicationException("rmi服务监听启动失败,发生重复绑定对象异常！",e);
		// } catch (MalformedURLException e) {
		// logger.error("rmi服务监听启动失败,发生URL畸形异常！",e);
		// throw new CommunicationException("rmi服务监听启动失败,发生URL畸形异常！",e);
		// } catch(Exception e){
		// logger.error("rmi服务监听启动失败,发生未知异常！",e);
		// throw new CommunicationException("rmi服务监听启动失败,发生未知异常！",e);
		// }
	}

	public void shutdown() {

		// try {
		// CommunicationConfiguration configuration =
		// CommunicationConfiguration.getConfiguration();
		// String url
		// ="rmi://"+configuration.getAddress()+":"+configuration.getPort()+"/rmiservice";
		// Naming.unbind(url);
		// } catch (RemoteException e) {
		// logger.error("rmi服务监听注销失败,创建远程对象发生异常！",e);
		// } catch (MalformedURLException e) {
		// logger.error("rmi服务监听注销失败,创建远程对象发生异常！",e);
		// } catch (NotBoundException e) {
		// logger.error("rmi服务监听注销失败,创建远程对象发生异常！",e);
		// } catch(Exception e){
		// logger.error("rmi服务监听注销失败,发生未知异常！",e);
		// }
	}

	/* 启动项名称 */
	public String getName() {
		return "rmi 服务";
	}
}
