package com.gongsibao.igirl.dto.ChangeTradeMark;

import java.util.List;

public class ChangeTradeMarkToRoBotDto {
    private int code = 200;
    private int count;
    private String pin = "123456789";
    private String msg = "msg ok!";
    private List<ChangeTradeMarkDto> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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

    public List<ChangeTradeMarkDto> getData() {
        return data;
    }

    public void setData(List<ChangeTradeMarkDto> data) {
        this.data = data;
    }
}
