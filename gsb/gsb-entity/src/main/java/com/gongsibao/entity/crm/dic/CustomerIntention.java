package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  CustomerIntention   
 * @Description:TODO 以下未最终确定
 * @author: 韩伟
 * @date:   2018年1月9日 下午7:53:11   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum CustomerIntention implements IEnum{

	A_1(1,IntentionCategory.A, "未跟进"), 
	A_2(2,IntentionCategory.A,  "初步跟进"), 
	B_1(3,IntentionCategory.B,  "意向签单"), 
	B_2(4,IntentionCategory.B,  "已签单"), 
	C_1(5,IntentionCategory.C,  "无效客户"), 
	C_2(6,IntentionCategory.C,  "流失客户"), 
	D_1(7,IntentionCategory.D,  "渠道合作"), 
	D_2(8,IntentionCategory.D,  "潜在客户");
	
	private int value;
	private String text;
	private IntentionCategory category;
	
	CustomerIntention(int value,IntentionCategory category,String text) {
		this.value = value;
		this.text = text;
		this.category = category;
	}

	@JsonCreator
	public static CustomerIntention getItem(int value) {

		for (CustomerIntention item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public String getText() {
		return this.text;
	}

	public Integer getValue() {

		return this.value;
	}

	public IntentionCategory getCategory() {
		return category;
	}
}
