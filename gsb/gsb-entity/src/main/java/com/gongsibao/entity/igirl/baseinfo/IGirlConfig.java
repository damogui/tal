package com.gongsibao.entity.igirl.baseinfo;

import com.gongsibao.entity.igirl.dict.ConfigType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_base_config",header = "尼斯数据批次")
public class IGirlConfig  extends Entity{
    @Column(name = "config_type", header = "申请人类型")
    private ConfigType configType;

    @Column(name = "configValue",header = "类型值")
    private String configValue;

    public ConfigType getConfigType() {
        return configType;
    }

    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
