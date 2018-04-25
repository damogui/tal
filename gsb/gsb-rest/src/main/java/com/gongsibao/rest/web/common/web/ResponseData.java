package com.gongsibao.rest.web.common.web;
/**
 * ClassName: ResponseData
 * @Description: TODO 返回结果
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/11 15:25
 */
public class ResponseData {

    public static int SUCCESS = 200;
    public static int FAIL = -1;
    /**
     * 系统未登录
     */
    public static int UNLOGIN = 401;
    /**
     * 未关注公众号
     */
    public static int UNSUBSCRIBE = 402;
    /**
     * 微信未登录
     */
    public static int UNAUTH = 403;
    public static int EXCEPTION = 500;

    public static final boolean SUCCESS_SUCCESS = true;
    public static final boolean SUCCESS_FAIL = false;

    private int code = 200;
    private String msg = "";
    private Object data;

    public static ResponseData getSuccess(Object data, String msg) {
        ResponseData result = new ResponseData();
        result.setData(data);
        result.setMsg(msg);
        result.setCode(SUCCESS);
        return result;
    }


    public static ResponseData getError(int code) {
        return getError(code, null);
    }

    public static ResponseData getError(int code, String msg) {
        ResponseData result = new ResponseData();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResponseData getError(Object data, int code, String msg) {
        ResponseData result = new ResponseData();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static ResponseData getException() {
        ResponseData result = new ResponseData();
        result.setCode(EXCEPTION);
        result.setMsg("您的网络不稳定，请稍后再试。");
        return result;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
