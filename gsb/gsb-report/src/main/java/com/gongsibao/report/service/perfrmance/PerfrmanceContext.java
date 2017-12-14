package com.gongsibao.report.service.perfrmance;


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

}
