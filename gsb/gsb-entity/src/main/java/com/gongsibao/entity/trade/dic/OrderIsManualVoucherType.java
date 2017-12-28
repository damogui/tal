package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderIsManualVoucherType implements IEnum {

	// 1、回款跨月
	// 2、星河互联代付、金牛座、微信商城等标记异常
	// 3、【确认收入】时借贷方金额都为零（金额太小，如：0.01元）
	// 4、【退款】时借贷方金额都为零（金额太小，如：0.01元）
	// 5、结转订单

	Hkky(1, "回款跨月"), 
	Esyc(2, "星河互联代付、金牛座、微信商城等标记异常"), 
	Qrsrjdwl(3, "【收款】【确认收入】时借贷方金额都为零（金额太小，如：0.01元）"), 
	Tkjdwl(4, "【退款】时借贷方金额都为零（金额太小，如：0.01元）"), 
	Jzdd(5, "结转订单"), 
	khmcwk(6, "客户名称为空"), 
	ywywk(7, "业务员为空");

	private int value;
	private String text;

	OrderIsManualVoucherType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderIsManualVoucherType getItem(int value) {

		for (OrderIsManualVoucherType item : values()) {

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
