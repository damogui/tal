package com.netsharp.rest.config.weixin;

import org.netsharp.startup.IStartup;
import org.netsharp.util.StopWatch;
import org.netsharp.wx.pa.startup.StartupWeixin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
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
				startups.add(new StartupWeixin());
            }

            return startups;
        }
    }
}
