package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 纳税模式
 */
public enum SellingDemandTaxMode implements IEnum {

	XIAOGUIMO(1, "小规模纳税人"), YIBAN(2, "一般纳税人");
	private int value;
	private String text;

	SellingDemandTaxMode(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SellingDemandTaxMode getItem(int value) {

		for (SellingDemandTaxMode item : values()) {

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
