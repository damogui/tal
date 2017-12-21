package com.gongsibao.report.base;


import java.util.Date;
import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.report.PerformanceStatistics;

public interface IPerformanceStatisticsService  extends IPersistableService<PerformanceStatistics>{

	/**   
	 * @Title: generateAll   
	 * @Description: TODO(生成所有)   
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean generate(Date date,int[] departmentIdIds);
	
	
	/**   
	 * @Title: updateParentId   
	 * @Description: TODO(更新父级关系)   
	 * @param: @param parentId
	 * @param: @param idList
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean updateParentId(Integer parentId, List<Integer> idList);
	
}
