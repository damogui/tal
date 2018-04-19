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
	WAITCHECKLIST(4, "等待材料审查"),
	WAITCHECKCONTENT(5, "等待内容审查"),
    AUDITING(6, "审查中"),
    ROLLBACK(7, "退回修改"),
	CHECKPASS(8, "材料审查通过"),
    ASSISTAUDITPASS(9, "辅助审查通过"),
	CHECKUNPASS(10, "材料审查未通过"),
	ACCEPTED(11, "工商机关已受理"),
	TOBECONFIRMED(12, "待确认"),
	APPROVED(13, "已核准"),
	DISMISSAL(14, "已驳回"),
    ENDED(15, "已终止"),
    TERMINATION(16, "业务终止");

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
