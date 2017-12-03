package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum FranchiseeReportType implements IEnum{

	year(1, "年"),
	month(2, "月"),
	week(3, "周"),
	date(4, "日");
	private int value;
	private String text;

	FranchiseeReportType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static FranchiseeReportType getItem(int value) {

		for (FranchiseeReportType item : values()) {

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
