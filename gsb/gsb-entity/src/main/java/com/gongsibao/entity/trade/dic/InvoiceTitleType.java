package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 发票抬头类型
 * @author angang
 *
 */
public enum InvoiceTitleType implements IEnum  {

	GR(0, "个人"), 
	DW(1, "单位"); 
	private int value;
	private String text;

	InvoiceTitleType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static InvoiceTitleType getItem(int value) {

		for (InvoiceTitleType item : values()) {

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
