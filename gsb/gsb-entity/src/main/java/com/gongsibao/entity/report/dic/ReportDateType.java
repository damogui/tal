package com.gongsibao.entity.report.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ReportDateType implements IEnum{

	YEAR(1, "年"),
	SEASON(2, "季"),
	MONTH(3, "月"),
	WEEK(4, "周"),
	DAY(5, "日");
	
	private int value;
	private String text;
	ReportDateType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ReportDateType getItem(int value) {

		for (ReportDateType item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {

		return this.value;
	}
}
