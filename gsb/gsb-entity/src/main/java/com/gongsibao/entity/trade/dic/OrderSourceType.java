package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderSourceType implements IEnum  {
	
	wu(0, "无"), 	
	Pc(3041, "pc"),
	H5(3042, "h5"),
	IOS(3043, "IOS"),
	Android(3044, "android"),
	Ht(3045, "后台"),
	Ddh5(3047, "钉钉h5"),
	Icompany(3048, "icompany"), 
	Wdwxd(3049, "万达微信端");
	private int value;
	private String text;

	OrderSourceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderSourceType getItem(int value) {

		for (OrderSourceType item : values()) {

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
