package com.gongsibao.franchisee.service.report;

import java.util.Date;

import com.gongsibao.utils.DateUtils;

public class ReportContext {
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getYear() {
		return DateUtils.getYear(date);
	}

	public Integer getSeason() {
		return DateUtils.getSeason(date);
	}

	public Integer getMonth() {
		return DateUtils.getMonth(date);
	}

	public Integer getWeek() {
		return DateUtils.getWeekOfYear(date);
	}

	public Integer getDay() {
		return DateUtils.getPassDayOfMonth(date);
	}
}
