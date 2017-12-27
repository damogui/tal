package com.gongsibao.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.service.CacheStartup;
import org.netsharp.job.core.JobStartup;
import org.netsharp.log.LogStartup;
import org.netsharp.startup.IStartup;
import org.netsharp.util.StopWatch;

import com.gongsibao.session.StartupUser;

@WebListener
public class StartupLisenter implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent evnt) {

		StartupManager startup = new StartupManager();
		{
			startup.startup();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent evnt) {

		StartupManager startup = new StartupManager();
		{
			startup.shutdown();
		}
	}

	public class StartupManager {

		private Log logger = LogFactory.getLog(StartupManager.class.getSimpleName());

		public void startup() {
			
			StopWatch sw = new StopWatch();

			List<IStartup> startups = this.getStartups();

			for (IStartup startup : startups) {

				if (startup.startCondition()) {
					
					logger.warn("startup:" + startup.getName());
					
					sw.start();
					
					startup.startup();
					
					sw.stop();
					
					logger.warn("startup:" + startup.getName() +"  耗时："+sw.getEclipsed());
				}
			}
		}

		public void shutdown() {

			List<IStartup> startups = this.getStartups();

			for (IStartup startup : startups) {
				startup.shutdown();

				logger.warn("shutdown:" + startup.getName());
			}
		}

		private List<IStartup> getStartups() {

			List<IStartup> startups = new ArrayList<IStartup>();
			{
				startups.add(new LogStartup());
				startups.add(new StartupUser());
//				startups.add(new StartupWeixin());
				startups.add(new CacheStartup());
				startups.add(new JobStartup());
//				startups.add(new RmiStartup());
//				startups.add(new StartupRest());
			}

			return startups;
		}
	}
}
