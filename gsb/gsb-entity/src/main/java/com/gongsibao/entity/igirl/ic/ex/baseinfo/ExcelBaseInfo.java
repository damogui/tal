package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import java.util.List;

@Table(name = "ic_ex_base_info",header = "注册申请单基础内容")
public class ExcelBaseInfo {
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

    @Subs(foreignKey = "icExRegisterCaseId",header = "内部成员",subType = Member.class)
    private List<Member> members;

    @Column(name = "operation_scope",header = "经营范围")
    private String operationScope;

    @Column(name = "ex_register_case_id",header = "工商注册申请单Id")
    private Integer exRegisterCaseId;

    @Reference(foreignKey = "exRegisterCaseId",header = "工商注册申请单")
    private IcExRegisterCase exRegisterCase;

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

    public Integer getExRegisterCaseId() {
        return exRegisterCaseId;
    }

    public void setExRegisterCaseId(Integer exRegisterCaseId) {
        this.exRegisterCaseId = exRegisterCaseId;
    }

    public IcExRegisterCase getExRegisterCase() {
        return exRegisterCase;
    }

    public void setExRegisterCase(IcExRegisterCase exRegisterCase) {
        this.exRegisterCase = exRegisterCase;
    }
}
