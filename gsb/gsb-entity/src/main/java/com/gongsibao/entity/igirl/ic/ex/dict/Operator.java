package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Operator implements IEnum{
    LEGAL_PERSON(0,"leijuan","lj123456","雷娟"),
    EXECUTIVE_DIRECTOR(1,"汉唐信通（北京）登记注册代理事务所-姜凯宁","12345678ab","姜凯宁"),
    SUPERVISOR(2,"niunan","12345678ab","牛楠")
    ;
    private Integer value;
    private String password;
    private String username;
    private String text;

    Operator(Integer value, String password,String username,String text) {
        this.value = value;
        this.password = password;
        this.username = username;
        this.text = text;
    }

    @JsonCreator
    public static Operator getItem(int value) {

        for (Operator item : values()) {

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
