package com.gongsibao.entity.crm;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_player")
public class CompanyPlayer extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6230552625093105953L;
	@Column(name="company_id")
    private Integer companyId;
    private String name;
    private String sex;
    private String telephone;
    private String mobile;
    private String email;
    @Column(name="is_complete")
    private Integer isComplete;
    private String address;
    private String education;
    @Column(name="certificate_type")
    private String certificateType;
    @Column(name="certificate_no")
    private String certificateNo;
    @Column(name="province_name")
    private String provinceName;
    private Integer capital;
    @Column(name="capital_percent")
    private Integer capitalPercent;
    @Column(name="stock_type")
    private Integer stockType;
    @Column(name="paid_years")
    private String paidYears;
    @Column(name="bus_no")
    private String busNo;
    private String nation;
    private String political;
    private String remark;
    @Column(name="add_time")
    private Date addTime;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getIsComplete() {
        return isComplete;
    }
    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getCertificateType() {
        return certificateType;
    }
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
    public String getCertificateNo() {
        return certificateNo;
    }
    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public Integer getCapital() {
        return capital;
    }
    public void setCapital(Integer capital) {
        this.capital = capital;
    }
    public Integer getCapitalPercent() {
        return capitalPercent;
    }
    public void setCapitalPercent(Integer capitalPercent) {
        this.capitalPercent = capitalPercent;
    }
    public Integer getStockType() {
        return stockType;
    }
    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }
    public String getPaidYears() {
        return paidYears;
    }
    public void setPaidYears(String paidYears) {
        this.paidYears = paidYears;
    }
    public String getBusNo() {
        return busNo;
    }
    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getPolitical() {
        return political;
    }
    public void setPolitical(String political) {
        this.political = political;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}