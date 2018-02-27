package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
public class NProductType extends Entity {
    @Column(name = "pid", header = "产品Id")
    private  Integer pId;
    @Column(name = "type_level", header = "类型级别")
    private  Integer typeLevel;
    @Column(name = "type_name", header = "类型名称")
    private  String typeName;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
