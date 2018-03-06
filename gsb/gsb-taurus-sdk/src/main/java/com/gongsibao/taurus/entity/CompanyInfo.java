package com.gongsibao.taurus.entity;



/**
 * Created by wk on 2018/1/30.
 * 公司详情
 */
public class CompanyInfo implements IEntity {

    private static final long serialVersionUID = -4375666888968506741L;

    /* 主键id */
    private long id;
    /* 归属省份的首字母小写 */
    private String base;
    /* 公司名称 */
    private String name;
    /* 法人ID */
    private long legalPersonId;
    /* 法人姓名 */
    private String legalPersonName;
    /* 法人类型，1 人 2 公司 */
    private int legalPersonType;
    /* 注册号 */
    private String regNumber;
    /* 公司类型代码 */
    private int companyType;
    /* 公司类型 */
    private String companyOrgType;
    /* 注册地址 */
    private String regLocation;
    /* 成立日期 */
    private String estiblishTime;
    /* 营业期限开始日期 */
    private String fromTime;
    /* 营业期限终止日期 */
    private String toTime;
    /* 经营范围 */
    private String businessScope;
    /* 登记机关 */
    private String regInstitute;
    /* 核准日期 */
    private String approvedTime;
    /* 核准机关 */
    private String orgApprovedInstitute;
    /* 企业状态 */
    private String regStatus;
    /* 注册资金 */
    private String regCapital;
    /* 实收注册资金 */
    private String actualCapital;
    /* 组织机构代码 */
    private String orgNumber;
    /* 1表示parse过，0表示没parse，3表示parse出错 */
    private int flag;
    /* 上级机构ID */
    private long parentId;
    /* 修改时间（每次修改系统自动更新） */
    private String updatetime;
    /* 上市代码 */
    private String listCode;
    /* 母公司控股比例 */
    private String ownershipStake;
    /* 数据来源标志 */
    private String sourceFlag;
    /* 根据名称parse出的公司后缀类型 */
    private String nameSuffix;
    /* 解析完成时间 */
    private String crawledtime;
    /* 统一社会信用代码 */
    private String property1;
    /* 新公司名id */
    private String property2;
    /* 英文名 */
    private String property3;
    /* 纳税人识别号 */
    private String property4;
    /* 通过工商app抓取时间 */
    private String property5;

    /*  */
    private String categoryName3;
    /*  */
    private String categoryName1;
    /*  */
    private String categoryName2;
    /*  */
    private String esKey;
    /*  */
    private String categoryCode3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(long legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public int getLegalPersonType() {
        return legalPersonType;
    }

    public void setLegalPersonType(int legalPersonType) {
        this.legalPersonType = legalPersonType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    public String getCompanyOrgType() {
        return companyOrgType;
    }

    public void setCompanyOrgType(String companyOrgType) {
        this.companyOrgType = companyOrgType;
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation;
    }

    public String getEstiblishTime() {
        return estiblishTime;
    }

    public void setEstiblishTime(String estiblishTime) {
        this.estiblishTime = estiblishTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getRegInstitute() {
        return regInstitute;
    }

    public void setRegInstitute(String regInstitute) {
        this.regInstitute = regInstitute;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getOrgApprovedInstitute() {
        return orgApprovedInstitute;
    }

    public void setOrgApprovedInstitute(String orgApprovedInstitute) {
        this.orgApprovedInstitute = orgApprovedInstitute;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getActualCapital() {
        return actualCapital;
    }

    public void setActualCapital(String actualCapital) {
        this.actualCapital = actualCapital;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getOwnershipStake() {
        return ownershipStake;
    }

    public void setOwnershipStake(String ownershipStake) {
        this.ownershipStake = ownershipStake;
    }

    public String getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(String sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getCrawledtime() {
        return crawledtime;
    }

    public void setCrawledtime(String crawledtime) {
        this.crawledtime = crawledtime;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property3) {
        this.property3 = property3;
    }

    public String getProperty4() {
        return property4;
    }

    public void setProperty4(String property4) {
        this.property4 = property4;
    }

    public String getProperty5() {
        return property5;
    }

    public void setProperty5(String property5) {
        this.property5 = property5;
    }

    public String getCategoryName3() {
        return categoryName3;
    }

    public void setCategoryName3(String categoryName3) {
        this.categoryName3 = categoryName3;
    }

    public String getCategoryName1() {
        return categoryName1;
    }

    public void setCategoryName1(String categoryName1) {
        this.categoryName1 = categoryName1;
    }

    public String getCategoryName2() {
        return categoryName2;
    }

    public void setCategoryName2(String categoryName2) {
        this.categoryName2 = categoryName2;
    }

    public String getEsKey() {
        return esKey;
    }

    public void setEsKey(String esKey) {
        this.esKey = esKey;
    }

    public String getCategoryCode3() {
        return categoryCode3;
    }

    public void setCategoryCode3(String categoryCode3) {
        this.categoryCode3 = categoryCode3;
    }
}
