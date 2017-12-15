package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderIsManualVoucher implements IEnum  {

	
//	1、回款跨月
//	2、e支付、刷卡等标记异常
//	3、【确认收入】时借贷方金额都为零（金额太小，如：0.01元）
//	4、【退款】时借贷方金额都为零（金额太小，如：0.01元）
//	5、结转订单
	
	Hkky(1, "回款跨月"), 
	Esyc(2, "e支付、刷卡等标记异常"),
	Qrsrjdwl(3, "【确认收入】时借贷方金额都为零（金额太小，如：0.01元）"),
	Tkjdwl(4, "【退款】时借贷方金额都为零（金额太小，如：0.01元）"),
	Jzdd(5, "结转订单");
	
	private int value;
	private String text;

	OrderIsManualVoucher(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderIsManualVoucher getItem(int value) {

		for (OrderIsManualVoucher item : values()) {

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
