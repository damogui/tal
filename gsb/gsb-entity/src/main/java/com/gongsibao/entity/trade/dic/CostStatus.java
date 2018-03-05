package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CostStatus  implements IEnum{

	NOENTRY(0, "未录入"), ONGOING(1, "录入中"), FINISHED(2, "完成录入");
	private int value;
	private String text;

	CostStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CostStatus getItem(int value) {

		for (CostStatus item : values()) {

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
