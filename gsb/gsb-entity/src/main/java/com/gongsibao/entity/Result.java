package com.gongsibao.entity;

import com.gongsibao.entity.dict.ResponseStatus;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用返回
 * @param <T>
 */
public class Result<T> {
    private ResponseStatus status = ResponseStatus.SUCCESS;
    private String msg;
    private T obj;
    private List<T> list;
    private Map<String, Object> extend = new LinkedHashMap<>();

    public Result() {
    }

    public Result(ResponseStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<String, Object> getExtend() {
        if (null == extend) {
            extend = new LinkedHashMap<>();
        }
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    @JsonIgnore
    public static boolean isSuccess(Result result) {
        if (null == result || null == result.getStatus()) {
            return false;
        }
        return result.getStatus().getValue() == ResponseStatus.SUCCESS.getValue();
    }
}
