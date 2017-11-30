package com.gongsibao.entity.crm;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_buy")
public class CompanyBuy extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1235112721795040523L;
	@Column(name="contact_name")
    private String contactName;
    @Column(name="contact_mobile")
    private String contactMobile;
    private String condition;
    @Column(name="reg_capital_type")
    private Integer regCapitalType;
    @Column(name="set_up_year")
    private Integer setUpYear;
    @Column(name="city_id")
    private Integer cityId;
    private String remark;
    private Integer status;
    @Column(name="follow_user_id")
    private Integer followUserId;
    @Column(name="add_time")
    private Date addTime;

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactMobile() {
        return contactMobile;
    }
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public Integer getRegCapitalType() {
        return regCapitalType;
    }
    public void setRegCapitalType(Integer regCapitalType) {
        this.regCapitalType = regCapitalType;
    }
    public Integer getSetUpYear() {
        return setUpYear;
    }
    public void setSetUpYear(Integer setUpYear) {
        this.setUpYear = setUpYear;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getFollowUserId() {
        return followUserId;
    }
    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}