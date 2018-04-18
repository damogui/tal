package com.gongsibao.entity.igirl.ic.ex;

import com.gongsibao.entity.crm.Customer;
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
    private Customer customer;

    @Column(name = "approval_name",header = "核准公司名称")
    private String approvalName;

    @Column(name = "approval_type",header = "审核状态")
    private ApprovalType approvalType;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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
}
