package com.gongsibao.igirl.tm.dto;

public class TradeMarkDto {
	private int tmId;//商标id

    private String tmName;//商标名称

    private String tmState;//商标状态

	public int getTmId() {
		return tmId;
	}

	public void setTmId(int tmId) {
		this.tmId = tmId;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getTmState() {
		return tmState;
	}

	public void setTmState(String tmState) {
		this.tmState = tmState;
	}
    
}
