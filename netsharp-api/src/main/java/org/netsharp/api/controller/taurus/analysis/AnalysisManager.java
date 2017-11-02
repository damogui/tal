package org.netsharp.api.controller.taurus.analysis;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.api.controller.taurus.analysis.service.AnalysisBasciService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisCopyrightService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisEntBranchService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisEntertainmentService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisStatisticsService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisTelecomService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisTmService;
import org.netsharp.api.controller.taurus.analysis.service.AnalysisYearReportService;
import org.netsharp.api.controller.taurus.dto.AnalysisDTO;

/**   
 * @ClassName:  AnalysisManager   
 * @Description:TODO 分析管理
 * @author: 韩伟
 * @date:   2017年10月30日 下午9:06:20   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class AnalysisManager {

	
	/**   
	 * @Title: getAnalysisDTO   
	 * @Description: TODO(计算分析结果)   
	 * @param: @param context
	 * @param: @return      
	 * @return: AnalysisDTO      
	 * @throws   
	 */
	public static AnalysisDTO getAnalysisDTO(AnalysisContext context){
		
		List<IAnalysisService> serviceList = new ArrayList<IAnalysisService>();
		serviceList.add(new AnalysisBasciService());
		serviceList.add(new AnalysisCopyrightService());
		serviceList.add(new AnalysisEntBranchService());
		serviceList.add(new AnalysisEntertainmentService());
		serviceList.add(new AnalysisStatisticsService());
		serviceList.add(new AnalysisTelecomService());
		serviceList.add(new AnalysisTmService());
		serviceList.add(new AnalysisYearReportService());
		for (IAnalysisService service : serviceList) {

			service.run(context);
		}
		
		return context.getAnalysisDTO();
	}
}
