package com.gongsibao.api.dto.ma;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * Created by win on 2018/2/2.
 */
public enum MaResponseCodeEnum implements IEnum {
    normal(1, "1-001"),//正常
    paraError(2, "1-002"),//参数不对
    serverError(3, "1-003");//服务器异常



    private int value;
    private String text;

    MaResponseCodeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    };


    @JsonCreator
    public static MaResponseCodeEnum getItem(int value) {

        for (MaResponseCodeEnum item : values()) {

            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Integer getValue() {
        return  this.value;
    }
}
