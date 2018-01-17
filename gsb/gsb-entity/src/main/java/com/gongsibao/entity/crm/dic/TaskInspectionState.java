package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  TaskRandomInspectionState   
 * @Description:TODO 任务抽查状态
 * @author: 韩伟
 * @date:   2018年1月17日 下午2:41:29   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum TaskInspectionState implements IEnum {


	UNINSPECTION(1, "未抽查"), NORMAL(2, "抽查正常"), ABNORMAL(3, "抽查异常"), PROCESSED(4, "异常已处理");
	private int value;
	private String text;

	TaskInspectionState(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TaskInspectionState getItem(int value) {

		for (TaskInspectionState item : values()) {

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
