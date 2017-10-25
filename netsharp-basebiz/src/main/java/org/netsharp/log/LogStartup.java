package org.netsharp.log;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.netsharp.startup.IStartup;
import org.netsharp.util.StringManager;

public class LogStartup implements IStartup {
	
	public boolean startCondition() {
		
		/*ubuntu下如果使用这个startup，启动就特别慢，不知道为什么*/
		
		return false;
	}

	public void startup() {

		URL path = this.getClass().getResource("/log4j.lnx.properties");

		String osname = System.getProperties().getProperty("os.name");

		if (!StringManager.equals(osname, "linux", true)) {
			path = this.getClass().getResource("/log4j.properties");
		}

		PropertyConfigurator.configure(path);
	}
	
	public void shutdown(){
	}

	@Override
	public String getName() {
		return "日志初始化";
	}
}
