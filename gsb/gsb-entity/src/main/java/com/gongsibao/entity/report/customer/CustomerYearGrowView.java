package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(name="report_customeryeargrow_view",isView=true)
public class CustomerYearGrowView extends BaseCustomerGrowView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String years;

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}
	
}
