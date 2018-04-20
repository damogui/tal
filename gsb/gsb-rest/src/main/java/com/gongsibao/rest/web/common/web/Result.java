package com.gongsibao.rest.web.common.web;

import com.gongsibao.rest.web.common.exception.BaseException;

import java.io.Serializable;

/**
 *
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 封装返回结果
 * @date 2018/4/16 15:51
 */
public class Result<T extends Object> implements Serializable{
    private static final long serialVersionUID = 7447938651449258944L;
    private static int SUCCESS = 200;
    private static int FAIL = -1;
    /** 系统未登录 **/
    private static int UN_LOGIN = 401;
    /** 未关注公众号**/
    private static int UN_SUBSCRIBE = 402;
    /** 微信未登录 **/
    private static int UN_AUTH = 403;
    private static int EXCEPTION = 500;

    private boolean success;
    private int code;
    private String msg;
    private T data;

    public interface Command<D>{
        D execute();
    }

    /**
     * 特殊需求#重置返回消息,仅限成功时
     *
     * @param message 返回消息
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    public <T> Result<T> resetOkMsg(String message) {
        if (this.isSuccess() && SUCCESS == this.getCode()) {
            this.setMsg(message);
        }
        return (Result<T>) this;
    }

    /**
     * 特殊需求#重置返回消息,仅限成功时 (抓取data返回信息 & 重置data),适用于强迫症用户
     *
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    public <T> Result<T> resetOkMsgFromData() {
        if (this.isSuccess() && SUCCESS == this.getCode()) {
            if (this.getData() != null && this.getData() instanceof String) {
                this.setMsg(this.getData().toString());
                this.setData(null);
            }
        }
        return (Result<T>) this;
    }

    public static <D> Result<D> build(Command<D> cmd) {
        D data = null;
        Result<D> result = new Result<D>();
        try {
            data = cmd.execute();
            result.setSuccess(true);
            result.setMsg("操作成功");
            result.setData(data);
            result.setCode(SUCCESS);
        }catch (BaseException baseException) {
            result.setMsg(baseException.getMessage());
            result.setData(data);
            result.setSuccess(false);
            result.setCode(FAIL);
        } catch (Throwable e) {
            result.setData(data);
            result.setSuccess(false);
            result.setMsg("您的网络不稳定，请稍后再试。");
            result.setCode(EXCEPTION);
            e.printStackTrace();
        }
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
