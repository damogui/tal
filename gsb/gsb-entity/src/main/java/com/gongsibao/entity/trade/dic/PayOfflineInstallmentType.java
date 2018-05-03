package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PayOfflineInstallmentType implements IEnum  {

	wk(-1, "尾款"), 
	qk(0, "全款"), 
	sk(1, "首款"), 
	eq(2, "二期"), 
	sanq(3, "三期"), 
	siq(4, "四期");
	private int value;
	private String text;

	PayOfflineInstallmentType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PayOfflineInstallmentType getItem(int value) {

		for (PayOfflineInstallmentType item : values()) {

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