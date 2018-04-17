package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum IndustryType implements IEnum{
    TRADE(0,"商贸","主营业务:其他综合零售"),
    SCIENCE(1,"科技","主营业务:其他科技推广和应用服务业"),
    INFORMATION_CONSULTATION(2,"信息咨询","主营业务:社会经济咨询"),
    SCIENCE_DEVELOPMENT(3,"科技发展","主营业务:其他科技推广和应用服务业"),
    DEPARTMENT_STORE(4,"百货","主营业务:其他综合零售");

    private Integer value;
    private String text;
    private String content;

    IndustryType(Integer value, String text,String content) {
        this.value = value;
        this.text = text;
        this.content = content;
    }

    @JsonCreator
    public static IndustryType getItem(int value) {

        for (IndustryType item : values()) {

            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public Integer getValue() {

        return this.value;
    }

    public String getContent() {
        return content;
    }
}
