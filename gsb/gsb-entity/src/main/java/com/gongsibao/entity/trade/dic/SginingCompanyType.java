package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 签约公司
 * @author angang
 *
 */
public enum SginingCompanyType  implements IEnum{

	SGINING_COMPANY_1(3161, "汉唐信通（北京）咨询股份有限公司"), 
	SGINING_COMPANY_2(3162, "汉唐信通（北京）科技有限公司"),
	SGINING_COMPANY_3(3163, "汉唐信通（北京）登记注册代理事务所"),
	SGINING_COMPANY_4(3164, "上海诺唐商务咨询有限公司"),
	SGINING_COMPANY_5(3165, "汉世唐源（北京）咨询有限公司"),
	SGINING_COMPANY_6(3166, "唐源"),
	SGINING_COMPANY_7(3167, "信通"),
	SGINING_COMPANY_8(3168, "掌迅东方（北京）信息技术有限公司"),
	SGINING_COMPANY_9(3169, "汉唐信通（北京）投资管理有限公司"),
	OTHER(31610, "其他");

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
