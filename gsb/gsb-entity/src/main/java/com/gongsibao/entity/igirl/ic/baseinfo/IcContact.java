package com.gongsibao.entity.igirl.ic.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_contact",header = "联系人")
public class IcContact extends Entity{
    @Column(name = "business_id",header = "企业联系人Id")
    private Integer businessId;

    @Reference(foreignKey = "businessId",header = "企业联系人Id")
    private IcBusiness business;

    @Column(name = "finance_id",header = "财务负责人Id")
    private Integer financeId;

    @Reference(foreignKey = "financeId",header = "财务负责人")
    private IcFinance finance;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public IcBusiness getBusiness() {
        return business;
    }

    public void setBusiness(IcBusiness business) {
        this.business = business;
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public IcFinance getFinance() {
        return finance;
    }

    public void setFinance(IcFinance finance) {
        this.finance = finance;
    }
}
