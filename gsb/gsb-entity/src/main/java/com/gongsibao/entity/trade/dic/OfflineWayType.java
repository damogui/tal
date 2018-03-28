package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OfflineWayType implements IEnum  {
		
	DGZZ(3111, "网银转账"),
	XJ(3112, "现金"), 
	SK(3113, "刷卡"), 
	GRZZ(3114, "个人转账"), 
	ALIYUN(3115, "阿里云代付"), 
	TENCENT(3116, "腾讯云代付"), 
	XHHL(3117, "星河互联代付"), 
	WEIXINSC(3118, "微信商城支付"),
	YYYDF(3119, "用友云代付"),
	ZFB(31110, "支付宝"),
	WX(31111, "微信"),
	EZF(31112, "e支付（财务二维码）"),
	JZ(31113, "结转"),
	JNZ(31114, "金牛座"),
	JDYS(31115, "京东益世");
	private int value;
	private String text;

	OfflineWayType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OfflineWayType getItem(int value) {

		for (OfflineWayType item : values()) {

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
