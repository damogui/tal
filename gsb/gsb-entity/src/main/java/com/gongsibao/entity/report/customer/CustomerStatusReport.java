package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(isView=true, name = "")
public class CustomerStatusReport extends BaseCustomerReportEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3772153347110609795L;
	
	/**   
     * @Fields date : TODO(客户状态===名称)   
     */   
    private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
