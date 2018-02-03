package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName: ChangeType
 * @Description:TODO 客户任务流转日志
 * @author: 韩伟
 * @date: 2018年1月9日 下午7:03:30
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public enum ChangeType implements IEnum {

	INPUT(1, "录入"), ALLOCATION(2, "分配"), SHARE(3, "分享"), RELEASE(4, "释放"), TRANSFER(5, "转移"), RECYCLE(6, "收回"), LOOK(7, "查看");

	private int value;
	private String text;

	ChangeType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ChangeType getItem(int value) {

		for (ChangeType item : values()) {

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
