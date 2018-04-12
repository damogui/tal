package com.gongsibao.entity.igirl.ic.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name = "ic_ba_notice",header = "补充信息")
public class IcBaNotice {
    @Column(name = "nameseal",header = "法定名称章")
    private String nameseal;

    @Column(name = "financeseal",header = "财务专用章")
    private String financeseal;

    @Column(name = "invoiceseal",header = "发票专用章")
    private String invoiceseal;

    @Column(name = "contractseal",header = "合同专用章")
    private String contractseal;

    @Column(name = "area",header = "刻章区")
    private String area;

    @Column(name = "name",header = "刻章单位名称")
    private String name;

    public String getNameseal() {
        return nameseal;
    }

    public void setNameseal(String nameseal) {
        this.nameseal = nameseal;
    }

    public String getFinanceseal() {
        return financeseal;
    }

    public void setFinanceseal(String financeseal) {
        this.financeseal = financeseal;
    }

    public String getInvoiceseal() {
        return invoiceseal;
    }

    public void setInvoiceseal(String invoiceseal) {
        this.invoiceseal = invoiceseal;
    }

    public String getContractseal() {
        return contractseal;
    }

    public void setContractseal(String contractseal) {
        this.contractseal = contractseal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
