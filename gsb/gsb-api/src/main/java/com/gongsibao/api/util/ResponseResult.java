package com.gongsibao.api.util;

import com.gongsibao.api.dto.ma.MaResponseCodeEnum;
import com.gongsibao.taurus.util.StringManager;

public class ResponseResult {

    private String code;

    private String message;

    private Object data;

    public String getCode() {
        if (StringManager.isNullOrEmpty(code)) {
            this.code = MaResponseCodeEnum.normal.getText();
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;

    }

    public String getMessage() {
        if(StringManager.isNullOrEmpty (message)){
            this.message="ok";


        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
