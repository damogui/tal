package com.gongsibao.entity.supplier.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  ResponseTime   
 * @Description:响应时间
 * @author: 韩伟
 * @date:   2018年2月9日 上午10:48:31   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum ResponseTime implements IEnum{

	MINUTE_5(5, "5分钟内"), 
	MINUTE_10(10, "10分钟内"),
	MINUTE_20(20, "20分钟内"), 
	MINUTE_30(30, "30分钟内"),
	MINUTE_60(60, "1小时内"), 
	MINUTE_240(240, "4小时内"),
	MINUTE_480(480, "8小时内");
	private int value;
	private String text;
	ResponseTime(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ResponseTime getItem(int value) {

		for (ResponseTime item : values()) {

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
