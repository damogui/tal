package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderProdTraceType implements IEnum {

	wu(0, "无"), 
	Ggzt(3151, "更改状态"), 
	Bz(3152, "备注"), 
	Sccl(3153, "上传材料"), 
	Tskf(3154, "提示客户"), 
	Kd(3155, "快递"), 
	Zhmm(3156, "帐号密码"), 
	Bjts(3157, "标记投诉"), 
	Txfzr(3158, "提醒负责人"), 
	Blmc(3159, "办理名称"), 
	Hqh(3160, "申请号"), 
	Ghywy(31502, "更换业务员");
	private int value;
	private String text;

	OrderProdTraceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderProdTraceType getItem(int value) {

		for (OrderProdTraceType item : values()) {

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
