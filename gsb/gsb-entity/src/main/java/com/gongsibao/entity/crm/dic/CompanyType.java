package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CompanyType implements IEnum{

	CompanyType_0(0, "地域+公司名+行业特征+公司类型"), 
	CompanyType_2(1, "公司名+（地域）+行业特征+公司类型"), 
	CompanyType_3(2, "公司名+行业特征+（地域）+公司类型");
	private int value;
	private String text;
	CompanyType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CompanyType getItem(int value) {

		for (CompanyType item : values()) {

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
