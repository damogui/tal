package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(name="report_customerweekgrow_view",isView=true)
public class CustomerWeekGrowView extends BaseCustomerGrowView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String weeks;

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
}
