package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PayReceiptStatus implements IEnum  {

	NotStarted(0, "未开始"), 
	Finished(1, "已完成");
	private int value;
	private String text;

	PayReceiptStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PayReceiptStatus getItem(int value) {

		for (PayReceiptStatus item : values()) {

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
