package com.gongsibao.entity.igirl.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_base_config",header = "尼斯数据批次")
public class IGirlConfig  extends Entity{
    @Column(name = "ig_code",header = "编号")
    private String code;
    @Column(name = "ig_name",header = "名称")
    private String name;
    @Column(name = "ig_value",header = "内容")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
