package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(name="report_customermonthgrow_view",isView=true)
public class CustomerMonthGrowView extends BaseCustomerGrowView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String months;

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}
}
