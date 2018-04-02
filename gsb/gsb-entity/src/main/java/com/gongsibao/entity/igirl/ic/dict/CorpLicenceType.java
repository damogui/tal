package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 证件类型
 * @author cyx
 *
 */
public enum CorpLicenceType implements IEnum {

	IDC(0, "中华人民共和国居民身份证"),
	ARMIRETIRED(1, "军人离(退)休证"),
	OTHER(2, "其他有效身份证件"),
	ARMI(3, "中华人民共和国军官证"),
	POLICE(4, "中华人民共和国警官证");

	private int value;
	private String text;

	CorpLicenceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpLicenceType getItem(int value) {

		for (CorpLicenceType item : values()) {

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
