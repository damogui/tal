package com.gongsibao.rest.common.web;

import lombok.Data;

/**
 * ClassName: WebResult
 * @Description: TODO 返回结果
 * @author bhpeng <bhpeng@gongsibao.com>
 * @date 2018/4/11 15:25
 */
@Data
public class WebResult {

    public static int SUCCESS = 0;
    public static int FAIL = 1;
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

    private int code = 0;
    private int status = 0;
    private String msg = "";
    private Object obj;
    private Object data;
    private Object list;
    private long total;
    private boolean success = true;

    public static WebResult getSuccess(Object data) {
        return getSuccess(data, 0, null);
    }

    public static WebResult getSuccess(Object data, long total) {
        return getSuccess(data, total, null);
    }

    public static WebResult getSuccess(Object data, long total, String msg) {
        WebResult result = new WebResult();
        result.setList(data);
        result.setTotal(total);
        result.setMsg(msg);
        result.setStatus(SUCCESS);
        return result;
    }

    public static WebResult getSuccess(Object data, String msg) {
        WebResult result = new WebResult();
        result.setList(data);
        result.setMsg(msg);
        result.setStatus(SUCCESS);
        return result;
    }


    public static WebResult getError(int code) {
        return getError(code, null);
    }

    public static WebResult getError(int code, String msg) {
        WebResult result = new WebResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static WebResult getError(Object data, int code, String msg) {
        WebResult result = new WebResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static WebResult getSuccess(String msg) {
        return getSuccess(null, 0, msg);
    }


}
