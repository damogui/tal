package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 住所（经营场所）,生产经营地
 * @author cyx
 *
 */
public enum CorpBjZone implements IEnum {

	DONGCHENG(0, "东城区"),
	XICHENG(1, "西城区"),
	CHANGYANG(2, "朝阳区"),
	HAIDIAN(3, "海淀区"),
	FENGTAI(4, "丰台区"),
	SHIJINGSHAN(5, "石景山区"),
	TONGZHOU(6, "通州区"),
	DAXING(7, "大兴区"),
	SHUNYI(8, "顺义区"),
	CHANGPING(9, "昌平区"),
	FANGSHAN(10, "房山区"),
	MENTOUGOU(11, "门头沟区"),
	PINGGU(12, "平谷区"),
	HUAIROU(13, "怀柔区"),
	MIYUN(14, "密云区"),
	YANQING(15, "延庆区"),
	KAIFAQU(16, "北京经济技术开发区");
	private int value;
	private String text;

	CorpBjZone(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpBjZone getItem(int value) {

		for (CorpBjZone item : values()) {

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
