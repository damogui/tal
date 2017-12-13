package com.gongsibao.entity.report.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ReportOrganizationType implements IEnum{


	SALESMAN(1, "业务员"),
	DEPARTMENT(2, "部门");
	
	private int value;
	private String text;
	ReportOrganizationType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ReportOrganizationType getItem(int value) {

		for (ReportOrganizationType item : values()) {

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
