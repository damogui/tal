package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 营业期限。4.2这里有问题
 * @author cyx
 *
 */
public enum CorpTimeLimite implements IEnum {
	// TODO 4-3工商网站正常后，修改一下。这个是错误的
	SETUP(0, "设立"),
	CHANGE(1, "变更"),
	AUTH(2, "备案"),
	ADDMOD(3, "增减补换照"),
	PLEDGE(4, "股权出质"),
	LOGOUT(5, "注销");
	private int value;
	private String text;

	CorpTimeLimite(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpTimeLimite getItem(int value) {

		for (CorpTimeLimite item : values()) {

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
