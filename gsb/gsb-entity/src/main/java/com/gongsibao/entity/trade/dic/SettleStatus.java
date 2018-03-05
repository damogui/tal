package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum SettleStatus implements IEnum{

	NO_SETTLEMENT(0, "未结算"), PORTION_SETTLEMENT(1, "部分结算"), FINISHED_SETTLEMENT(2, "已结算");
	private int value;
	private String text;

	SettleStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SettleStatus getItem(int value) {

		for (SettleStatus item : values()) {

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
