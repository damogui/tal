package org.netsharp.nsf.provider;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.ReflectManager;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

public class Provider {
	private static final Logger log = Logger.getLogger(Provider.class);

	/**
	 * 启动服务
	 * 
	 * @param applicationName
	 *            应用程序
	 * @param registryAddress
	 *            ZK地址 ：127.0.0.1:2181
	 * @param services
	 *            服务,版本 services.put(
	 *            "org.netsharp.organization.base.IEmployeeService", "1.0.1");
	 * @throws IOException
	 */
	public static void start(String applicationName, String registryAddress, Map<String, String> services) throws IOException {
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName(applicationName);
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		{
			registry.setAddress("zookeeper://" + registryAddress);
		}
		// 服务提供者协议配置
		ProtocolConfig protocol = new ProtocolConfig();
		{
			protocol.setName("dubbo");
			protocol.setPort(20880);
			protocol.setThreads(200);
		}
		MonitorConfig monitorConfig = new MonitorConfig();
		{
			monitorConfig.setProtocol("registry");
		}
		// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

		for (String inter : services.keySet()) {

			Class<?> type = ReflectManager.getType(inter);
			Object serviceObject = ServiceFactory.create(type);

			ServiceConfig<Object> service = new ServiceConfig<Object>(); // 服务提供者暴露服务配置。此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
			service.setApplication(application);
			service.setRegistry(registry); // 多个注册中心可以用setRegistries()
			service.setProtocol(protocol); // 多个协议可以用setProtocols()
			service.setInterface(type);
			service.setRef(serviceObject);// 服务实现
			service.setVersion(services.get(inter));
			service.setTimeout(120000);
			service.setMonitor(monitorConfig);
			// 暴露及注册服务
			service.export();
		}

		log.info("服务已经启动...");
		System.in.read();
	}
}
