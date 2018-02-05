package com.gongsibao.api.config;

import com.gongsibao.api.dto.ma.MaResponseCodeEnum;

import java.io.IOException;

/**
 * Created by win on 2018/2/5.
 */
public class DeviceException  extends IOException {
    private String code;
    private String message;

    /**
     * 构造异常类
     * @param code
     */
    public DeviceException( String code,String message ) {
        this.code = code;
        this.message=message;

    }

    public DeviceException( String code) {
        this.code = code;

    }



    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

