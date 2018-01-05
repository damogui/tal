package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
//发票类型
public enum InvoiceType implements IEnum  {

	Pt(3081, "普通发票"), 
	Zzszy(3082, "增值税专用发票"); 
	private int value;
	private String text;

	InvoiceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static InvoiceType getItem(int value) {

		for (InvoiceType item : values()) {

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
