package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw 公司类型
 */
public enum CompanyType implements IEnum {

	YOUXIAN(1, "有限公司"), HEHUO(2, "有限合伙企业"), GUFEN(3, "股份有限公司"), JITUAN(4,
			"集团有限公司");
	private int value;
	private String text;

	CompanyType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CompanyType getItem(int value) {

		for (CompanyType item : values()) {

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
