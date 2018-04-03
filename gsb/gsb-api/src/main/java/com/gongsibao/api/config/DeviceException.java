package com.gongsibao.api.config;

import java.io.IOException;

/**
 * 股转接口自定义异常，处理股转的code返回和message返回
 */
public class DeviceException  extends IOException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8926934114859622348L;
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

