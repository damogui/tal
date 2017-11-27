package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum FollowStatus implements IEnum {

	FOLLOW_STATUS_1(4011, "未跟进"), 
	FOLLOW_STATUS_2(4012, "初步跟进"), 
	FOLLOW_STATUS_3(4013, "意向签单"), 
	FOLLOW_STATUS_4(4014, "已签单"), 
	FOLLOW_STATUS_5(4015, "无效客户"), 
	FOLLOW_STATUS_6(4016, "流失客户"), 
	FOLLOW_STATUS_7(4017, "渠道合作"), 
	FOLLOW_STATUS_8(4020, "潜在客户");
	private int value;
	private String text;

	FollowStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static FollowStatus getItem(int value) {

		for (FollowStatus item : values()) {

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