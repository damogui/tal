package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderProdUserMapType implements IEnum {

	Ywy(3061, "业务员"), 
	Kfy(3062, "客服（关注）"),
	Czy(3063, "操作员");
	private int value;
	private String text;

	OrderProdUserMapType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderProdUserMapType getItem(int value) {

		for (OrderProdUserMapType item : values()) {

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
