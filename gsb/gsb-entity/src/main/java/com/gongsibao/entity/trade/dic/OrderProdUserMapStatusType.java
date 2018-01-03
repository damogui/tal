package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderProdUserMapStatusType implements IEnum {

	Zzfz(3141, "正在负责"), 
	Cjfz(3142, "曾经负责");
	private int value;
	private String text;

	OrderProdUserMapStatusType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderProdUserMapStatusType getItem(int value) {

		for (OrderProdUserMapStatusType item : values()) {

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
