package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//结转状态
public enum OrderCarryStatusType implements IEnum{
	
	wu(0, "无"), 
	Dsh(3031, "待审核"), 
	Tkz(3032, "结转中"),
	Tkwc(3033, "结转完成"),
	Bhtk(3034, "驳回结转");
	private int value;
	private String text;

	OrderCarryStatusType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderCarryStatusType getItem(int value) {

		for (OrderCarryStatusType item : values()) {

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
