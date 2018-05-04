package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 公司登记业务类型
 * @author cyx
 *
 */
public enum CorpRegType implements IEnum {

	SETUP(0, "设立"),
	CHANGE(1, "变更"),
	AUTH(2, "备案"),
	ADDMOD(3, "增减补换照"),
	PLEDGE(4, "股权出质"),
	LOGOUT(5, "注销");
	private int value;
	private String text;

	CorpRegType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpRegType getItem(int value) {

		for (CorpRegType item : values()) {

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
