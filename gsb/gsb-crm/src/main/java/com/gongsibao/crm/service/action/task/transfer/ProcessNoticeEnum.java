package com.gongsibao.crm.service.action.task.transfer;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ProcessNoticeEnum implements IEnum{
	salesmanTosalesman(1, "业务员商机转移给业务员"),
	salesmanToseas(2, "业务员商机转移给公海"),
	seasTosalesman(3, "公海商机转移给业务员"),
	seasToseas(4, "公海商机转移给公海");
	
	private int value;
	private String text;

	ProcessNoticeEnum(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ProcessNoticeEnum getItem(int value) {

		for (ProcessNoticeEnum item : values()) {

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
