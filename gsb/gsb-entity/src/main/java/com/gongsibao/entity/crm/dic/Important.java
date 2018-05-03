package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Important implements IEnum {

	COMMON(4021, "普通"), INTERMEDIATE(4022, "中级"), HIGHGRADE(4023, "高级"), VIP(4024, "VIP"), INVALID(4025, "无效");
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