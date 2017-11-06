package com.gongsibao.session;

import org.netsharp.panda.startup.SessionManagerImpl;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.startup.IStartup;

public class StartupUser implements IStartup {
	
	public boolean startCondition() {
		return true;
	}

	public void startup() {
		
		SessionManager.initialize(new SessionManagerImpl());
	}
	
	public void shutdown(){
	}

	public String getName() {
		return "用户Session";
	}

}
