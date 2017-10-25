package org.netsharp.nsf.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;

public class ConsumerFactory {
	/**
	 * 获取服务,类似ServiceFactory.create(type);
	 * 
	 * @param applicationName
	 *            应用程序名
	 * @param registryAddress
	 *            ZK地址
	 * @param version
	 *            服务版本
	 * @param type
	 *            服务类型
	 * @return service
	 */
	public static Object create(String applicationName, String registryAddress, String version, Class<?> type) {
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName(applicationName);

		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		{
			registry.setAddress("zookeeper://" + registryAddress);
		}

		// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

		// 引用远程服务
		ReferenceConfig<Object> reference = new ReferenceConfig<Object>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		{
			reference.setApplication(application);
			reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
			reference.setInterface(type);
			reference.setVersion(version);
			reference.setTimeout(120000);
		}
		// 使用缓存获取
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		return cache.get(reference);
	}
}
