package com.gongsibao.entity.igirl.ic.ex;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_register_case",header = "公司注册信息登记表")
public class IcExRegisterCase extends Entity {
    @Column(name = "customer_id",header = "客户ID")
    private Integer customerId;

    @Reference(foreignKey = "customerId",header = "客户")
    private NCustomer customer;

    private String customerMobile;

    private String customerName;

    @Column(name = "approval_name",header = "核准公司名称")
    private String approvalName;

    @Column(name = "approval_type",header = "审核状态")
    private ApprovalType approvalType = ApprovalType.WAIT;

    @Column(name = "corp_reg_statue",header = "工商业务状态")
    private CorpRegStatue corpRegStatue;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public NCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(NCustomer customer) {
        this.customer = customer;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public ApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(ApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    public CorpRegStatue getCorpRegStatue() {
        return corpRegStatue;
    }

    public void setCorpRegStatue(CorpRegStatue corpRegStatue) {
        this.corpRegStatue = corpRegStatue;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
