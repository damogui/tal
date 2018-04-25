package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OperatorType implements IEnum{
    LEI_JUAN(0,"leijuan","lj123456","雷娟"),
    JIANG_KAI_NING(1,"汉唐信通（北京）登记注册代理事务所-姜凯宁","12345678ab","姜凯宁"),
    NIU_NAN(2,"niunan","12345678ab","牛楠");
    private Integer value;
    private String password;
    private String username;
    private String text;

    OperatorType(Integer value, String username,String password,String text) {
        this.value = value;
        this.password = password;
        this.username = username;
        this.text = text;
    }

    @JsonCreator
    public static OperatorType getItem(int value) {

        for (OperatorType item : values()) {

            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Integer getValue() {

        return this.value;
    }
}
