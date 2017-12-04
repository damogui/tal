package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_trademark")
public class Trademark extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8456754527751580347L;
	@Column(name="company_id",header="CompanyId")
    private Integer companyId;
    @Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(header="jyid")
    private String jyid;
    @Column(name="reg_no",header="RegNo")
    private String regNo;
    @Column(name="int_cls",header="IntCls")
    private Integer intCls;
    @Column(name="int_cls_name",header="IntClsName")
    private String intClsName;
    @Column(header="name")
    private String name;
    @Column(name="int_cls_category",header="IntClsCategory")
    private String intClsCategory;
    @Column(name="app_date",header="AppDate")
    private Timestamp appDate;
    @Column(name="applicant_cn",header="ApplicantCn")
    private String applicantCn;
    @Column(name="applicant_en",header="ApplicantEn")
    private String applicantEn;
    @Column(header="category")
    private String category;
    @Column(name="id_card_no",header="IdCardNo")
    private String idCardNo;
    @Column(header="agent")
    private String agent;
    @Column(header="status")
    private Integer status;
    @Column(name="order_prod_id",header="OrderProdId")
    private Integer orderProdId;
    @Column(header="origin")
    private Integer origin;
    @Column(name="flow_status",header="FlowStatus")
    private String flowStatus;
    @Column(name="flow_status_desc",header="FlowStatusDesc")
    private String flowStatusDesc;
    @Column(name="flow_status_detail_desc",header="FlowStatusDetailDesc")
    private String flowStatusDetailDesc;
    @Column(name="has_image",header="HasImage")
    private Integer hasImage;
    @Column(name="image_url",header="ImageUrl")
    private String imageUrl;
    @Column(name="address_cn",header="AddressCn")
    private String addressCn;
    @Column(name="address_en",header="AddressEn")
    private String addressEn;
    @Column(name="announcement_issue",header="AnnouncementIssue")
    private Integer announcementIssue;
    @Column(name="announcement_date",header="AnnouncementDate")
    private Timestamp announcementDate;
    @Column(name="is_formal",header="IsFormal")
    private Integer isFormal;
    @Column(header="applicant1")
    private String applicant1;
    @Column(header="applicant2")
    private String applicant2;
    @Column(header="color")
    private String color;
    @Column(name="reg_issue",header="RegIssue")
    private Integer regIssue;
    @Column(name="reg_date",header="RegDate")
    private Timestamp regDate;
    @Column(name="hou_qi_zhi_ding_date",header="HouQiZhiDingDate")
    private Timestamp houQiZhiDingDate;
    @Column(name="guo_ji_zhu_ce_date",header="GuoJiZhuCeDate")
    private Timestamp guoJiZhuCeDate;
    @Column(name="you_xian_quan_date",header="YouXianQuanDate")
    private Timestamp youXianQuanDate;
    @Column(name="valid_period",header="ValidPeriod")
    private String validPeriod;
    @Column(name="expiry_date",header="ExpiryDate")
    private Timestamp expiryDate;
    @Column(name="start_date",header="StartDate")
    private Timestamp startDate;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getJyid() {
        return jyid;
    }
    public void setJyid(String jyid) {
        this.jyid = jyid;
    }
    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    public Integer getIntCls() {
        return intCls;
    }
    public void setIntCls(Integer intCls) {
        this.intCls = intCls;
    }
    public String getIntClsName() {
        return intClsName;
    }
    public void setIntClsName(String intClsName) {
        this.intClsName = intClsName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIntClsCategory() {
        return intClsCategory;
    }
    public void setIntClsCategory(String intClsCategory) {
        this.intClsCategory = intClsCategory;
    }
    public Timestamp getAppDate() {
        return appDate;
    }
    public void setAppDate(Timestamp appDate) {
        this.appDate = appDate;
    }
    public String getApplicantCn() {
        return applicantCn;
    }
    public void setApplicantCn(String applicantCn) {
        this.applicantCn = applicantCn;
    }
    public String getApplicantEn() {
        return applicantEn;
    }
    public void setApplicantEn(String applicantEn) {
        this.applicantEn = applicantEn;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getIdCardNo() {
        return idCardNo;
    }
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
    public String getAgent() {
        return agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getOrderProdId() {
        return orderProdId;
    }
    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }
    public Integer getOrigin() {
        return origin;
    }
    public void setOrigin(Integer origin) {
        this.origin = origin;
    }
    public String getFlowStatus() {
        return flowStatus;
    }
    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }
    public String getFlowStatusDesc() {
        return flowStatusDesc;
    }
    public void setFlowStatusDesc(String flowStatusDesc) {
        this.flowStatusDesc = flowStatusDesc;
    }
    public String getFlowStatusDetailDesc() {
        return flowStatusDetailDesc;
    }
    public void setFlowStatusDetailDesc(String flowStatusDetailDesc) {
        this.flowStatusDetailDesc = flowStatusDetailDesc;
    }
    public Integer getHasImage() {
        return hasImage;
    }
    public void setHasImage(Integer hasImage) {
        this.hasImage = hasImage;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getAddressCn() {
        return addressCn;
    }
    public void setAddressCn(String addressCn) {
        this.addressCn = addressCn;
    }
    public String getAddressEn() {
        return addressEn;
    }
    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }
    public Integer getAnnouncementIssue() {
        return announcementIssue;
    }
    public void setAnnouncementIssue(Integer announcementIssue) {
        this.announcementIssue = announcementIssue;
    }
    public Timestamp getAnnouncementDate() {
        return announcementDate;
    }
    public void setAnnouncementDate(Timestamp announcementDate) {
        this.announcementDate = announcementDate;
    }
    public Integer getIsFormal() {
        return isFormal;
    }
    public void setIsFormal(Integer isFormal) {
        this.isFormal = isFormal;
    }
    public String getApplicant1() {
        return applicant1;
    }
    public void setApplicant1(String applicant1) {
        this.applicant1 = applicant1;
    }
    public String getApplicant2() {
        return applicant2;
    }
    public void setApplicant2(String applicant2) {
        this.applicant2 = applicant2;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Integer getRegIssue() {
        return regIssue;
    }
    public void setRegIssue(Integer regIssue) {
        this.regIssue = regIssue;
    }
    public Timestamp getRegDate() {
        return regDate;
    }
    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
    public Timestamp getHouQiZhiDingDate() {
        return houQiZhiDingDate;
    }
    public void setHouQiZhiDingDate(Timestamp houQiZhiDingDate) {
        this.houQiZhiDingDate = houQiZhiDingDate;
    }
    public Timestamp getGuoJiZhuCeDate() {
        return guoJiZhuCeDate;
    }
    public void setGuoJiZhuCeDate(Timestamp guoJiZhuCeDate) {
        this.guoJiZhuCeDate = guoJiZhuCeDate;
    }
    public Timestamp getYouXianQuanDate() {
        return youXianQuanDate;
    }
    public void setYouXianQuanDate(Timestamp youXianQuanDate) {
        this.youXianQuanDate = youXianQuanDate;
    }
    public String getValidPeriod() {
        return validPeriod;
    }
    public void setValidPeriod(String validPeriod) {
        this.validPeriod = validPeriod;
    }
    public Timestamp getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
}