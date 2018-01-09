package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum NotifiedType implements IEnum{
	Wx(1, "企业微信"), 
	Dd(2, "钉钉"), 
	Dx(3, "短信");
	private int value;
	private String text;

	NotifiedType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static NotifiedType getItem(int value) {

		for (NotifiedType item : values()) {

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
