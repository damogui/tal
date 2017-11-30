package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Important implements IEnum {

	IMPORTANT_1(4021, "普通"), IMPORTANT_2(4022, "中级"), IMPORTANT_3(4023, "高级"), IMPORTANT_4(4024, "VIP");
	private int value;
	private String text;

	Important(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static Important getItem(int value) {

		for (Important item : values()) {

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