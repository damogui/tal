package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 签约公司
 * @author angang
 *
 */
public enum SginingCompanyType  implements IEnum{

	CONSULT(3161, "汉唐信通（北京）咨询股份有限公司"), LTD(3162, "汉唐信通（北京）科技有限公司");
	
	private int value;
	private String text;

	SginingCompanyType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SginingCompanyType getItem(int value) {

		for (SginingCompanyType item : values()) {

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
