package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

import com.gongsibao.entity.u8.dic.SetOfBooksType;

public enum OrderManualVoucherStatus implements IEnum  {

	NotStarted(0, "未开始"), 
	Finished(1, "已完成");
	private int value;
	private String text;

	OrderManualVoucherStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderManualVoucherStatus getItem(int value) {

		for (OrderManualVoucherStatus item : values()) {

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