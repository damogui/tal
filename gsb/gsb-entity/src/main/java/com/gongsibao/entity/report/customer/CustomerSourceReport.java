package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(isView = true, name = "")
public class CustomerSourceReport extends BaseCustomerReportEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8920345947849515361L;

	/**   
     * @Fields date : TODO(名称)   
     */   
    private String sourceName;
    
    /**   
     * @Fields date : TODO(线上、线下)   
     */   
    private String lineName;

	

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
}
