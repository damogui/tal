package com.gongsibao.api.conroller.taurus.analysis;

import com.gongsibao.api.conroller.taurus.dto.AnalysisDTO;

import com.gongsibao.taurus.entity.EntRegistry;

/**   
 * @ClassName:  AnalysisContext   
 * @Description:TODO 分析上下文
 * @author: 韩伟
 * @date:   2017年10月30日 下午9:06:52   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class AnalysisContext {

	/**   
	 * @Fields companyName : TODO(公司名称)   
	 */
	private String companyName;
	
	/**   
	 * @Fields entRegistry : TODO(企业注册信息)   
	 */   
	private EntRegistry entRegistry;
	
	/**   
	 * @Fields analysisDTO : TODO(分析结果)   
	 */   
	private AnalysisDTO analysisDTO;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public AnalysisDTO getAnalysisDTO() {
		
		if(analysisDTO == null){
			
			analysisDTO = new AnalysisDTO();
		}
		return analysisDTO;
	}

	public void setAnalysisDTO(AnalysisDTO analysisDTO) {
		this.analysisDTO = analysisDTO;
	}

	public EntRegistry getEntRegistry() {
		return entRegistry;
	}

	public void setEntRegistry(EntRegistry entRegistry) {
		this.entRegistry = entRegistry;
	}
}
