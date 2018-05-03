package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PriorityType implements IEnum {

	NONE(0,"priorityType1", "无"), FIRST(1,"priorityType2", "在先优先权"),SHOW(2,"priorityType3","展会优先权");
	//TODO(?)
	private Integer value;
	private String text;
	private String content;

	PriorityType(Integer value, String content, String text) {
		this.value = value;
		this.content = content;
		this.text = text;
	}

	@JsonCreator
	public static PriorityType getItem(Integer value) {

		for (PriorityType item : values()) {

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
		return value;
	}

	public String getContent() {
		return content;
	}
}
