package com.gongsibao.entity.igirl.ic.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name = "ic_fund_ba",header = "补充信息")
public class IcFundBa {
    @Column(name = "dwdz",header = "单位地址")
    private String dwdz;

    @Column(name = "dwyb",header = "邮政编码")
    private String dwyb;

    public String getDwdz() {
        return dwdz;
    }

    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }

    public String getDwyb() {
        return dwyb;
    }

    public void setDwyb(String dwyb) {
        this.dwyb = dwyb;
    }
}
