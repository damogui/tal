package com.gongsibao.report.service.perfrmance;

import java.util.Date;

public abstract class AbstractPerfrmanceService {

	
	/**
	 * @Fields context : TODO(报表统计上下文)
	 */
	protected PerfrmanceContext context;

	/**
	 * @Fields nextService : TODO(下一个统计服务)
	 */
	protected AbstractPerfrmanceService nextService;

	/**
	 * @Title: execute
	 * @Description: TODO(执行)
	 * @param: @param context
	 * @return: void
	 * @throws
	 */
	public void execute(Integer principalId, Date date) {
		
		this.before();

		this.doExecute(principalId, date);

		if (this.nextService != null) {

			this.nextServiceExecute(principalId, date);
		}
	}
	
	public abstract void before();

	public abstract void doExecute(Integer principalId, Date date);

	public void nextServiceExecute(Integer principalId, Date date) {

		this.nextService.execute(principalId, date);
	}

	public PerfrmanceContext getContext() {
		return context;
	}

	public void setContext(PerfrmanceContext context) {
		this.context = context;
	}

	public AbstractPerfrmanceService getNextService() {
		return nextService;
	}

	public void setNextService(AbstractPerfrmanceService nextService) {
		this.nextService = nextService;
	}
}
