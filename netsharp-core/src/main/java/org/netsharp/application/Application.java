package org.netsharp.application;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.dic.Environment;
import org.netsharp.util.NetUtil;
import org.netsharp.util.PropertyConfigurer;
import org.netsharp.util.StringManager;

public class Application {

	private static ApplicationContext context;
	private static final Log logger = LogFactory.getLog(Application.class.getName());

	private static PropertyConfigurer conf;
	private static final String confPropertyFileNames = "conf/configuration.properties";

	public static ApplicationContext getContext() {

		if (context == null) {

			context = new ApplicationContext();
			{
				conf = PropertyConfigurer.newInstance(confPropertyFileNames);

				// app.name -> name
				context.setName(conf.get("app.name"));
				{
					boolean isPool = conf.get("data.source.pool") == null ? false : Boolean.parseBoolean(conf.get("data.source.pool"));
					context.setDataSourcePool(isPool);
				}

				if (StringManager.isNullOrEmpty(context.getName())) {
					throw new NetsharpException("请配置：app.name(/conf/configuration.properties)");
				}

				// pp.packages.scan -> packagesToScan
				String packageToScan = conf.get("app.packages.scan");
				if (StringManager.isNullOrEmpty(packageToScan)) {

					context.setPackagesToScan(new String[] { "org.netsharp" });

					logger.warn("没有配置：app.packages.scan(/conf/configuration.properties)");
				} else {
					List<String> ss = new ArrayList<String>();
					{
						ss.add("org.netsharp");
					}

					String[] packages = packageToScan.split(",");
					for (String pack : packages) {
						if (!ss.contains(pack)) {
							ss.add(pack);
						}
					}

					String[] packagesToScan = new String[ss.size()];
					context.setPackagesToScan(ss.toArray(packagesToScan));

				}
				context.setEnvironment(getEnvironment());
				
				//系统默认密码
				String defaultPassword = conf.get("org.netsharp.defaultpassword");
				context.setDefaultPassword(defaultPassword);
			}
		}

		return context;
	}

	/**
	 * 设置运行环境
	 * 
	 * @param conf
	 * @return
	 */
	private static Environment getEnvironment() {
		Environment env = Environment.Dev;
		if (NetUtil.hasHostIp(conf.get("environment.ip.product"))) {
			logger.info("正在product服务器上运行");
			env = Environment.Product;
		} else if (NetUtil.hasHostIp(conf.get("environment.ip.test"))) {
			logger.info("正在test服务器上运行");
			env = Environment.Test;
		} else {
			logger.info("正在dev服务器上运行");
			env = Environment.Dev;
		}
		return env;
	}

	public static String getConfig(String key) {
		if (conf == null) {
			conf = PropertyConfigurer.newInstance(confPropertyFileNames);
		}
		return conf.get(key);
	}
}
