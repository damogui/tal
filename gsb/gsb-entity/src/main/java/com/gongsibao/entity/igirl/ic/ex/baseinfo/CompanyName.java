package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.ex.dict.IndustryType;
import com.gongsibao.entity.igirl.ic.ex.dict.NameStructureType;
import com.gongsibao.entity.igirl.ic.ex.dict.OrganizationalType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_company_name",header = "公司取名")
public class CompanyName extends Entity{
    @Column(name = "region",header = "地域")
    private String region = "北京";

    @Column(name = "ent_tra",header = "公司字号")
    private String entTra;

    @Column(name = "industry_type",header = "行业特点")
    private IndustryType industryType = IndustryType.DEPARTMENT_STORE;

    @Column(name = "organizational_type",header = "组织形式")
    private OrganizationalType organizationalType = OrganizationalType.LIMITED_COMPANY;

    @Column(name = "type",header = "名称结构")
    private NameStructureType type = NameStructureType.ONE;

    @Column(name = "ic_ex_base_info_id",header = "基础内容ID")
    private Integer excelBaseInfoId;

    @JsonIgnore
    @Reference(foreignKey = "excelBaseInfoId",header = "基础内容")
    private ExcelBaseInfo excelBaseInfo;

    @Column(name = "state",header = "状态")
    private boolean state = false;

    @Column(name = "name",header = "得到公司名")
    private String name;

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

    public Integer getExcelBaseInfoId() {
        return excelBaseInfoId;
    }

    public void setExcelBaseInfoId(Integer excelBaseInfoId) {
        this.excelBaseInfoId = excelBaseInfoId;
    }

    public ExcelBaseInfo getExcelBaseInfo() {
        return excelBaseInfo;
    }

    public void setExcelBaseInfo(ExcelBaseInfo excelBaseInfo) {
        this.excelBaseInfo = excelBaseInfo;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public NameStructureType getType() {
        return type;
    }

    public void setType(NameStructureType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
