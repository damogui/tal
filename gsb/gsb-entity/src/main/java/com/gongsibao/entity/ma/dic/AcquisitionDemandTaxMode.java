package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AcquisitionDemandTaxMode implements IEnum{


	UNLIMITED(0, "不限"), XIAOGUIMO(1, "小规模纳税人"), YIBAN(2, "一般纳税人");
	private int value;
	private String text;

	AcquisitionDemandTaxMode(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AcquisitionDemandTaxMode getItem(int value) {

		for (AcquisitionDemandTaxMode item : values()) {

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
