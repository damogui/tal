package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ConfigType  implements IEnum {
    IGIRL_JSON_IN(0,"IGIRL_JSON_IN", "数据源输入路径"),
    IGIRL_JSON_OUT(1,"IGIRL_JSON_OUT", "数据源输出路径"),
    IGIRL_JSON_NAME(2,"IGIRL_JSON_NAME","数据源文件名称");
    private Integer value;
    private String text;
    private String content;

    ConfigType(Integer value,String text,String content) {
        this.value = value;
        this.content = content;
        this.text = text;
    }

    @JsonCreator
    public static ConfigType getItem(Integer value) {

        for (ConfigType item : values()) {

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
        return value;
    }

    public String getContent() {
        return content;
    }
}
