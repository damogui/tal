package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
//支付成功记录
public enum PaySuccessStatus implements IEnum  {
	
	Weizhifu(3121, "未支付"), 
	DAishenhe(3122, "待审核"),
	Success(3123, "成功"),
	Finished(3124, "失败");
	private int value;
	private String text;

	PaySuccessStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PaySuccessStatus getItem(int value) {

		for (PaySuccessStatus item : values()) {

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