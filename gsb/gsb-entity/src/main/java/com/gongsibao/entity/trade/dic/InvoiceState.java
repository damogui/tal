package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum InvoiceState implements IEnum {

	AWAIT(0, "待开发票"), ALREADY(1, "已开发票"), DELIVER(2, "已投递");
	private int value;
	private String text;

	InvoiceState(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static InvoiceState getItem(int value) {

		for (InvoiceState item : values()) {

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
