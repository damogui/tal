package org.netsharp.job.core;

import org.netsharp.application.Application;
import org.netsharp.startup.IStartup;
import org.netsharp.util.NetUtil;

public class JobStartup implements IStartup {

	public boolean startCondition() {

		String machineIp = Application.getConfig("org.netsharp.job.machine.ip");
		if (NetUtil.hasHostIp(machineIp)) {

			return true;

		} else {

			return false;
		}

	}

	public void startup() {

		JobHelper.starts();
	}

	public void shutdown() {

		JobHelper.shutdowns();
	}

	public String getName() {
		return "作业管理";
	}

}
