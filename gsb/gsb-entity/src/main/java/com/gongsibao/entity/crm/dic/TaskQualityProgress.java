package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TaskQualityProgress implements IEnum {

	GOUP(1, "上升"), DECLINE(2, "下降"), INVARIABILITY(3, "不变");
	private int value;
	private String text;

	TaskQualityProgress(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TaskQualityProgress getItem(int value) {

		for (TaskQualityProgress item : values()) {

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
