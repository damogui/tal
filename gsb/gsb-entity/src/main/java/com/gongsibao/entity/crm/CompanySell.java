package com.gongsibao.entity.crm;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_sell",header="")
public class CompanySell extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5740389249163183228L;
	@Column(name="company_id",header="")
    private Integer companyId;
    @Column(name="contact_name",header="")
    private String contactName;
    @Column(name="contact_mobile",header="")
    private String contactMobile;
    @Column(name="sell_name",header="")
    private String sellName;
    @Column(name="city_id",header="")
    private Integer cityId;
    private Integer price;
    private Integer status;
    @Column(name="audit_status",header="")
    private Integer auditStatus;
    @Column(name="add_time",header="")
    private Date addTime;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
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
    public String getSellName() {
        return sellName;
    }
    public void setSellName(String sellName) {
        this.sellName = sellName;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}