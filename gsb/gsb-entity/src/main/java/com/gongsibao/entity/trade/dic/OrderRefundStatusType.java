package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderRefundStatusType implements IEnum  {
	
	wu(0, "无"), 
	Dsh(1051, "待审核"), 
	Tkz(1052, "审核中"),
	Tkwc(1053, "驳回审核"),
	Bhtk(1054, "审核通过"); 
	private int value;
	private String text;

	OrderRefundStatusType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderRefundStatusType getItem(int value) {

		for (OrderRefundStatusType item : values()) {

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
