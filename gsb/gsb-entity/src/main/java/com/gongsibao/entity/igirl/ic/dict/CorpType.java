package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 单位类型
 * @author cyx
 *
 */
public enum CorpType implements IEnum {

	ENTERPRISE(0, "企业法人"),
	LEGAL(1, "事业法人"),
	SOCIETY(2, "社团法人"),
	GOV(3, "机关法人"),
	FOREIGN(4, "外商投资企业"),
	PARTNERSHIP(5, "内资合伙企业"),
	FOREIGNPARTNERSHIP(6, "外商投资合伙企业"),
	OERSONAL(7, "个人独资企业"),
	OTHER(8, "其他非自然人投资者");

	private int value;
	private String text;

	CorpType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpType getItem(int value) {

		for (CorpType item : values()) {

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
