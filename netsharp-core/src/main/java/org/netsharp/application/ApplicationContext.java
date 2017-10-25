package org.netsharp.application;

import org.netsharp.dic.Environment;

/**
 * @author xufangbo 应用程序上下文信息，包括应用的名称等全局信息 有些信息从配置文件中读取，如name
 *         有些信息动态生成，如当前登陆用户个数等信息
 */
public class ApplicationContext {

	/**
	 * 应用程序名称 panda工作台页面的title显示，以及注册和修改密码等页面显示
	 * 从conf/configuration.properties配置文件中读取，key:app.name
	 */
	private String name;

	/**
	 * 系统要扫描的包 服务扫描、数据库同步时扫描等
	 * 从conf/configuration.properties配置文件中读取，key:pp.packages.scan
	 * org.netsharp包不需要配置
	 */
	private String[] packagesToScan;
	/**
	 * 环境检测 从conf/configuration.properties配置文件中读取
	 */
	private Environment environment;
	
	/**   
	 * @Fields defaultPassword :系统默认密码  
	 * #当前服务器是否启动作业，防止多台服务器作业重复执行
	 * org.netsharp.defaultpassword=123456 
	 */   
	private String defaultPassword;

	/**
	 * 是否使用数据库连接池
	 */
	private boolean dataSourcePool = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getPackagesToScan() {
		return packagesToScan;
	}

	public void setPackagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public boolean isDataSourcePool() {
		return dataSourcePool;
	}

	public void setDataSourcePool(boolean dataSourcePool) {
		this.dataSourcePool = dataSourcePool;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
}
