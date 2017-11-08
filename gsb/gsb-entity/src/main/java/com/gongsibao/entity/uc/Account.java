package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_account")
public class Account extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2827881401134957483L;
	private String name;
    private String passwd;
    private String ticket;
    private String email;
    @Column(name="mobile_phone")
    private String mobilePhone;
    private String telephone;
    @Column(name="head_thumb_file_id")
    private Integer headThumbFileId;
    @Column(name="real_name")
    private String realName;
    @Column(name="source_client_id")
    private Integer sourceClientId;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="is_bbk")
    private String isBbk="0";
    @Column(name="identity_card")
    private String identityCard;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Integer getHeadThumbFileId() {
        return headThumbFileId;
    }
    public void setHeadThumbFileId(Integer headThumbFileId) {
        this.headThumbFileId = headThumbFileId;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public Integer getSourceClientId() {
        return sourceClientId;
    }
    public void setSourceClientId(Integer sourceClientId) {
        this.sourceClientId = sourceClientId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
    public String getIdentityCard() {
        return identityCard;
    }
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}