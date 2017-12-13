package com.gongsibao.report.service.perfrmance;

import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;

/**   
 * @ClassName:  PerfrmanceContext   
 * @Description:TODO 业绩统计上下文
 * @author: 韩伟
 * @date:   2017年12月13日 下午8:50:13   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class PerfrmanceContext {

	/**   
	 * @Fields userId : TODO(业务员ID)   
	 */   
	private Integer salesmanId;
	
	/**   
	 * @Fields departmentId : TODO(部门Id)   
	 */   
	private Integer departmentId;
	
	/**   
	 * @Fields type : TODO(统计时间类型)   
	 */   
	private ReportDateType type;
	
	/**   
	 * @Fields organizationType : TODO(统计组织类型)   
	 */   
	private ReportOrganizationType organizationType;

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public ReportDateType getType() {
		return type;
	}

	public void setType(ReportDateType type) {
		this.type = type;
	}

	public ReportOrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(ReportOrganizationType organizationType) {
		this.organizationType = organizationType;
	}
}
