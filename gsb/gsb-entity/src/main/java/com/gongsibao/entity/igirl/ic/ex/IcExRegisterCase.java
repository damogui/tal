package com.gongsibao.entity.igirl.ic.ex;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyAddress;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyName;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Shareholder;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

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

    @Subs(foreignKey = "icExRegisterCaseId",header = "备选公司名称",subType = CompanyName.class)
    private List<CompanyName> companyNames;

    @Column(name = "capital",header = "注册资金(万元)")
    private Integer capital;

    @Column(name = "period",header = "经营期限")
    private Integer period;

    @Column(name = "pay_date",header = "注册资本实缴日期")
    private Integer payDate = 20;

    @Subs(foreignKey = "icExRegisterCaseId",header = "股东信息",subType = Shareholder.class)
    private List<Shareholder> shareholders;

    @Column(name = "own_land_type",header = "是否为自有地")
    private BooleanType ownLandType = BooleanType.YES;

    @Column(name = "domicile_id",header = "企业住所Id")
    private Integer domicileId;

    @Reference(foreignKey = "domicileId",header = "企业住所")
    private CompanyAddress domicile;

    @Reference(foreignKey = "icExRegisterCaseId",header = "内部成员")
    private List<Member> members;

    @Column(name = "operation_scope",header = "经营范围")
    private String operationScope;

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

    public List<CompanyName> getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(List<CompanyName> companyNames) {
        this.companyNames = companyNames;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPayDate() {
        return payDate;
    }

    public void setPayDate(Integer payDate) {
        this.payDate = payDate;
    }

    public List<Shareholder> getShareholders() {
        return shareholders;
    }

    public void setShareholders(List<Shareholder> shareholders) {
        this.shareholders = shareholders;
    }

    public BooleanType getOwnLandType() {
        return ownLandType;
    }

    public void setOwnLandType(BooleanType ownLandType) {
        this.ownLandType = ownLandType;
    }

    public Integer getDomicileId() {
        return domicileId;
    }

    public void setDomicileId(Integer domicileId) {
        this.domicileId = domicileId;
    }

    public CompanyAddress getDomicile() {
        return domicile;
    }

    public void setDomicile(CompanyAddress domicile) {
        this.domicile = domicile;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getOperationScope() {
        return operationScope;
    }

    public void setOperationScope(String operationScope) {
        this.operationScope = operationScope;
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
