package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TraceFileStatus implements IEnum{

	NEWEST(1, "最新"), HISTORY(2, "历史");
	
	private int value;
	private String text;

	TraceFileStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TraceFileStatus getItem(int value) {

		for (TraceFileStatus item : values()) {

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
