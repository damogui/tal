package com.gongsibao.report.service.perfrmance;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.report.base.IPerformanceStatisticsService;

public abstract class AbstractPerfrmanceService {

	protected IPersister<PerformanceStatistics> pm = PersisterFactory.create();
	
	protected IPerformanceStatisticsService statisticsService;

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
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param principalId 部门Id，业务员Id
	 * @param: @param date 统计日期
	 * @return: void
	 * @throws
	 */
	public void execute() {

		this.before();

		this.delete();
		
		this.doExecute();

		if (this.nextService != null) {

			this.nextService.execute();
		}
	}

	/**
	 * @Title: before
	 * @Description: TODO(补全)
	 * @param:
	 * @return: void
	 * @throws
	 */
	public abstract void before();
	
	/**   
	 * @Title: delete   
	 * @Description: TODO(删除)   
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	public abstract Boolean delete();

	public abstract void doExecute();


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

	public IPerformanceStatisticsService getStatisticsService() {

		if (statisticsService == null) {

			statisticsService = ServiceFactory.create(IPerformanceStatisticsService.class);
		}
		return statisticsService;
	}
}
