package com.gongsibao.entity.ncl;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ncl_batch",header = "尼斯数据批次")
public class NclBatch extends Entity{
    @Column(name = "code",header = "编号")
    private String code;

    @Column(name = "context",header = "说明")
    private String context;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
