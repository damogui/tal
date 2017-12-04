package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TrackProgress  implements IEnum{

	TRACK_PROGRESS_1(1, "未拜访"), 
	TRACK_PROGRESS_2(2, "电话拜访"),
	TRACK_PROGRESS_3(3, "陌拜"),
	TRACK_PROGRESS_4(4, "洽谈中"),
	TRACK_PROGRESS_5(5, "已合作"),
	TRACK_PROGRESS_6(6, "已中止"),
	TRACK_PROGRESS_7(7, "已合作中止");
	private int value;
	private String text;
	TrackProgress(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TrackProgress getItem(int value) {

		for (TrackProgress item : values()) {

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
