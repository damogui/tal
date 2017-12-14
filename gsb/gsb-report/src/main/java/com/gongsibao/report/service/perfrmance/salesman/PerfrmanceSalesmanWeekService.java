package com.gongsibao.report.service.perfrmance.salesman;

import java.util.Date;

import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

/**   
 * @ClassName:  PerfrmanceSalesmanWeekService   
 * @Description:TODO 业务员周统计，与月无关，单独统计
 * @author: 韩伟
 * @date:   2017年12月14日 下午2:25:36   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class PerfrmanceSalesmanWeekService extends AbstractPerfrmanceService{

	@Override
	public void before() {
		
		this.setNextService(new PerfrmanceSalesmanMonthService());
	}
	
	@Override
	public void doExecute(Integer principalId,Date date) {
		
		//执行单人月统计
		this.nextService.execute(principalId, date);
	}
}
