package com.gongsibao.igirl.dto.TradeMark;

import java.util.List;

public class TmForRobotDto {
	private String code = "200";
	private String count;
	private String pin = "123456789";
	private String msg = "msg ok!";
	private List<TradeMarkApplyInfo> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<TradeMarkApplyInfo> getData() {
		return data;
	}

	public void setData(List<TradeMarkApplyInfo> data) {
		this.data = data;
	}

}
