package com.gongsibao.api.dto.ma;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * Created by win on 2018/2/2.
 */
/*服务类型枚举*/
public enum ServiceTypeEnum  implements IEnum {
    p1(1, "不限"),
    p2(2, "企业包装"),
    p3(3, "上市壳公司"),
    p4(4, "类金融牌照"),
    p5(5, "资质类公司"),
    p6(6, "精选-热销"),
    p7(7, "公司类型"),
    p8(8, "公司性质"),
    p9(9, "成立年限") ;



    private int value;
    private String text;

    ServiceTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    };


    @JsonCreator
    public static ServiceTypeEnum getItem(int value) {

        for (ServiceTypeEnum item : values()) {

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
        return this.value;
    }
}

