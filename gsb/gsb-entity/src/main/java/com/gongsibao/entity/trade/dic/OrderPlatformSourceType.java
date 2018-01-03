package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//订单平台类别
public enum OrderPlatformSourceType implements IEnum  {
	
	Gsb(32101, "公司宝"), 
	Txzckj(32102, "腾讯众创空间"),
	Aly(32103, "阿里云"),
	Xhhl(32104, "星河互联"),
	Gys(32105, "供应商"),
	Wxsc(32106, "微信商城"),
	Yyy(32107, "用友云"),
	Dd(32108, "钉钉"),
	Icompany(32109, "icompany"),
	Jdzc(32110, "京东众筹"),
	Wd(32111, "万达"),
	Jdys(32112, "京东益世"),
	Bbk(32113, "八百客");
	
	private int value;
	private String text;

	OrderPlatformSourceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderPlatformSourceType getItem(int value) {

		for (OrderPlatformSourceType item : values()) {

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