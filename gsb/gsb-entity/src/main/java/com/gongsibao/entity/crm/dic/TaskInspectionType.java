package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 抽查类型（抽查、处理）
 * @author Administrator
 *
 */
public enum TaskInspectionType implements IEnum {


	INSPECT(1, "抽查"), PROCESSED(2, "处理");
	private int value;
	private String text;

	TaskInspectionType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TaskInspectionType getItem(int value) {

		for (TaskInspectionType item : values()) {

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
