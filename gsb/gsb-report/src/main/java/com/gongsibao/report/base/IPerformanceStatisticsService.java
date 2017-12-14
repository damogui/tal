package com.gongsibao.report.base;


import java.util.Date;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.report.PerformanceStatistics;

public interface IPerformanceStatisticsService   extends IPersistableService<PerformanceStatistics>{

	/**   
	 * @Title: generateAll   
	 * @Description: TODO(生成所有)   
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean generate(Date date);
	
}
