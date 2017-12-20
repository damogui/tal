package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(name="report_customerdaygrow_view",isView=true)
public class CustomerDayGrowView extends BaseCustomerGrowView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String days;
	
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	
}
