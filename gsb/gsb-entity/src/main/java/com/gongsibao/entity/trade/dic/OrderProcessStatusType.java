package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//支付成功记录
public enum OrderProcessStatusType implements IEnum  {
	
	Dbl(3021, "待办理"), 
	Zzbl(3022, "正在办理"),
	Yqx(3023, "已取消"),
	Ywc(3024, "已完成"),
	Dyhqr(3025, "待用户确认");
	
	private int value;
	private String text;

	OrderProcessStatusType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderProcessStatusType getItem(int value) {

		for (OrderProcessStatusType item : values()) {

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