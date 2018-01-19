package com.gongsibao.entity.gardian.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DeviceType implements IEnum {

	CLOUDSERVER(0, "云端服务器"), PYSERVER(1, "物理服务器"),ROUTER(2,"路由器"),SWITCH(3,"交换机"),AP(4,"AP");
	private int value;
	private String text;

	DeviceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DeviceType getItem(int value) {

		for (DeviceType item : values()) {

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
