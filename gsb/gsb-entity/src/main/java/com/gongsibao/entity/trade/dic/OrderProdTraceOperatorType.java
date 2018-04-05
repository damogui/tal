package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderProdTraceOperatorType implements IEnum {

	wu(0, "无"), 
	Ztth(3151, "更改状态"), 
	Czrbz(3152, "备注"), 
	Jkrbz(3153, "上传材料"), 
	Clshtg(3154, "提示客户"), 
	Clshbtg(3155, "快递"),
	Zhmm(3156, "帐号密码"),
	Bjts(3157, "标记投诉"),
	Txfzr(3158, "提醒负责人");
		
	private int value;
	private String text;

	OrderProdTraceOperatorType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderProdTraceOperatorType getItem(int value) {

		for (OrderProdTraceOperatorType item : values()) {

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