package com.gongsibao.entity.igirl.res;

import com.gongsibao.entity.igirl.dict.CaseConvertType;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConvertToOrderResult implements Serializable {

    private static final long serialVersionUID = 3731558225759634095L;

    // 转换状态
    private CaseConvertType convertType;

    // 扩展信息
    private Map<String, Object> extend = new LinkedHashMap<>();

    public ConvertToOrderResult() {
    }

    public ConvertToOrderResult(CaseConvertType convertType) {
        this.convertType = convertType;
    }


    public CaseConvertType getConvertType() {
        return convertType;
    }

    public void setConvertType(CaseConvertType convertType) {
        this.convertType = convertType;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }
}
