package com.gongsibao.entity.taurus.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DingtalkKeywordStatus implements IEnum{

	NORMAL(0, "正常 "), DELETE(1, "删除");
	private int value;
	private String text;

	DingtalkKeywordStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DingtalkKeywordStatus getItem(int value) {

		for (DingtalkKeywordStatus item : values()) {

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
