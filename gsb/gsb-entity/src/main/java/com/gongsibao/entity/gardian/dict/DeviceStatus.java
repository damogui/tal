package com.gongsibao.entity.gardian.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DeviceStatus implements IEnum {

	INUSE(0, "正常在用"), INSTOP(1, "故障停用"),FIXING(2,"维修中"),WAITING(3,"等待配件"),RETURN(4,"返厂维修中"),DEBUG(5,"调试安装中"),GIVEUP(6,"报废");
	private int value;
	private String text;

	DeviceStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DeviceStatus getItem(int value) {

		for (DeviceStatus item : values()) {

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
