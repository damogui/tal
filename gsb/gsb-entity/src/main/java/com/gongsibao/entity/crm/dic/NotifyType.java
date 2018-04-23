package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum NotifyType implements IEnum {

	WEIXIN(1, "微信"), DINGDING(2, "钉钉"), SMS(3, "短信"), EMAIL(4, "邮箱"), SYSTEM(5, "系统"), ALL(6, "全部");

	private int value;
	private String text;

	NotifyType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static NotifyType getItem(int value) {

		for (NotifyType item : values()) {

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
