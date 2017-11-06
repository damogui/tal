package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 公司性质
 */
public enum CompanyNature implements IEnum {

	NEIZI(1, "内资公司"), WAIZI(2, "外资公司"), HEZI(3, "合资公司"), HAIWAI(4, "海外公司");

	private int value;
	private String text;

	CompanyNature(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CompanyNature getItem(int value) {

		for (CompanyNature item : values()) {

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
