package com.gongsibao.entity.taurus.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum WalletType implements IEnum {

	RECHARGE(0, "充值"), CONSUMPTION(1, "消费"), DISCOUNT(2, "活动优惠");
	private int value;
	private String text;

	WalletType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static WalletType getItem(int value) {

		for (WalletType item : values()) {

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
