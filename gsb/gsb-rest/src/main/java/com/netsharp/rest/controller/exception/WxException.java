package com.netsharp.rest.controller.exception;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 常规业务异常
 * @date 2018/4/16 15:51
 */
@SuppressWarnings("serial")
public class WxException extends RuntimeException{
    private int code = 200;
    private String msg = "";
    public WxException(){
        super();
    }

    public WxException(String message){
        this.code = -1;
        this.msg = message;
    }
    public WxException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
