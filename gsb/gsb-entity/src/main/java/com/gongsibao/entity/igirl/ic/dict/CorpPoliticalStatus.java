package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 政治面目
 * @author cyx
 *
 */
public enum CorpPoliticalStatus implements IEnum {

	CPC(0, "中国共产党党员"),
	PROBATIONARY(1, "中国共产党预备党员"),
	CYL(2, "中国共产主义青年团团员"),
	RCCK(3, "中国国民党革命委员会会员"),
	CDL(4, "中国民主同盟盟员"),
	CDNCA(5, "中国民主建国会会员"),
	CAPD(6, "中国民主促进会会员"),
	CPWDP(6, "中国农工党党员"),
	CZGD(7, "中国致公党党员"),
	JSS(8, "九三学社社员"),
	TDSL(9, "台湾民主自治同盟盟员"),
	NOPARTY(10, "无党派民主人士"),
	MASS(11, "群众");


	private int value;
	private String text;

	CorpPoliticalStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpPoliticalStatus getItem(int value) {

		for (CorpPoliticalStatus item : values()) {

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
