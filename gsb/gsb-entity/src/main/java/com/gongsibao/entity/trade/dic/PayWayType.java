package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PayWayType  implements IEnum{

	ONLINE_PAYMENT(3101, "在线支付"), 
	OFFLINE_PAYMENT(3102, "线下支付"), 
	CARRY_FORWARD(3103, "内部结转");
	private int value;
	private String text;

	PayWayType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PayWayType getItem(int value) {

		for (PayWayType item : values()) {

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
