package com.gongsibao.entity.igirl.baseinfo;

import org.joda.time.DateTime;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.Date;

@Table(name = "ig_base_ncl_batch",header = "尼斯数据批次")
public class NclBatch extends Entity{
    @Column(name = "code",header = "编号")

    private String code = DateTime.now().toString("yyyyMMdd");

    @Column(name = "context",header = "说明")
    private String context;

    @Column(name = "current_status",header = "是否为当前版本")
    private boolean currentStatus = false;

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

    public boolean isCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(boolean currentStatus) {
        this.currentStatus = currentStatus;
    }
}
