package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OfflineWayType implements IEnum  {
		
	DGZZ(3111, "对公转账"), 
	XJ(3112, "现金"), 
	SK(3113, "刷卡"), 
	GRZZ(3114, "个人转账"), 
	ALIYUN(3115, "阿里云代付"), 
	TENCENT(3116, "腾讯云代付"), 
	XHHL(3117, "星河互联代付"), 
	WEIXIN(3118, "微信商城支付"); 
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
