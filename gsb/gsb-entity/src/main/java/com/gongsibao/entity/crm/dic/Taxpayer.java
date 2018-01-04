package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Taxpayer  implements IEnum{

	Taxpayer_1(1, "一般纳税人"), 
	Taxpayer_2(2, "小规模纳税人");
	private int value;
	private String text;
	Taxpayer(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static Taxpayer getItem(int value) {

		for (Taxpayer item : values()) {

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
