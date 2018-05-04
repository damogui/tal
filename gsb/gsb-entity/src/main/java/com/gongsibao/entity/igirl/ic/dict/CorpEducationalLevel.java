package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 文化程度
 * @author cyx
 *
 */
public enum CorpEducationalLevel implements IEnum {

	DOCTOR(0, "博士"),
	MASTER(1, "硕士"),
	POSTGRADUATE(2, "研究生"),
	BACJELOR(3, "大学本科（简称“大学”）"),
	COLLEGE(4, "大学专科和专科学校"),
	SECONDARY(5, "中等专科学校（简称“中专”）或中等技术学校(简称“中技”)[含工、农、林、财、艺、体、医、师范及其他]"),
	MECHANIC(6, "技工学校"),
	HIGH(7, "高中"),
	ILLITERACY(8, "文盲或半文盲");


	private int value;
	private String text;

	CorpEducationalLevel(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpEducationalLevel getItem(int value) {

		for (CorpEducationalLevel item : values()) {

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
