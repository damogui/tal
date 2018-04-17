package com.gongsibao.entity.igirl.ic.ex.baseinfo;


import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.IndustryType;
import com.gongsibao.entity.igirl.ic.ex.dict.OrganizationalType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_company_name",header = "公司取名")
public class CompanyName extends Entity{
    @Column(name = "region",header = "地域")
    private String region = "北京";

    @Column(name = "ent_tra",header = "公司字号")
    private String entTra = "";

    @Column(name = "industry_type",header = "行业特点")
    private IndustryType industryType = IndustryType.DEPARTMENT_STORE;

    @Column(name = "organizational_type",header = "组织形式")
    private OrganizationalType organizationalType = OrganizationalType.LIMITED_COMPANY;

    @Column(name = "ic_ex_register_case_id",header = "信息登记表ID")
    private Integer icExRegisterCaseId;

    @Reference(foreignKey = "icExRegisterCaseId",header = "信息登记表")
    private IcExRegisterCase icExRegisterCase;

    private String item = region+entTra+industryType.getText()+organizationalType.getText();

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEntTra() {
        return entTra;
    }

    public void setEntTra(String entTra) {
        this.entTra = entTra;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }

    public void setIndustryType(IndustryType industryType) {
        this.industryType = industryType;
    }

    public OrganizationalType getOrganizationalType() {
        return organizationalType;
    }

    public void setOrganizationalType(OrganizationalType organizationalType) {
        this.organizationalType = organizationalType;
    }

    public Integer getIcExRegisterCaseId() {
        return icExRegisterCaseId;
    }

    public void setIcExRegisterCaseId(Integer icExRegisterCaseId) {
        this.icExRegisterCaseId = icExRegisterCaseId;
    }

    public IcExRegisterCase getIcExRegisterCase() {
        return icExRegisterCase;
    }

    public void setIcExRegisterCase(IcExRegisterCase icExRegisterCase) {
        this.icExRegisterCase = icExRegisterCase;
    }
}
