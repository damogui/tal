package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 公司登记业务状态
 * @author cyx
 *
 */
public enum CorpRegStatue implements IEnum {

	UNCOMMITTED(0, "未提交"),
	UNAUDIT(1, "待审核"),
	AUDITPASS(2, "内容审查通过"),
	AUDITUNPASS(3, "内容审查未通过"),
	WAITCHECK(4, "等待材料审查"),
	CHECKPASS(5, "材料审查通过"),
	CHECKUNPASS(6, "材料审查未通过"),
	ACCEPTED(7, "工商机关已受理"),
	TOBECONFIRMED(8, "待确认"),
	APPROVED(9, "已核准"),
	DISMISSAL(10, "已驳回"),
	TERMINATION(11, "业务终止");
	private int value;
	private String text;

	CorpRegStatue(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpRegStatue getItem(int value) {

		for (CorpRegStatue item : values()) {

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
