package com.gongsibao.entity.acount;

import org.apache.commons.lang.StringUtils;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.dic.Important;

@Table(name = "uc_account")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 2827881401134957483L;

    @Column(name = "name", header = "名称")
    private String name;

    @Column(name = "passwd", header = "密码")
    private String passwd;

    @Column(name = "ticket", header = "ticket")
    private String ticket;

    @Column(name = "email", header = "邮箱")
    private String email;

    @Column(name = "mobile_phone", header = "手机号")
    private String mobilePhone;

    @Column(name = "telephone", header = "座机号")
    private String telephone;

    @Column(name = "head_thumb_file_id", header = "头像Id")
    private Integer headThumbFileId;

    @Column(name = "real_name", header = "姓名")
    private String realName;

    @Column(name = "source_client_id", header = "注册客户端序号，type=6")
    private Integer sourceClientId;

    @Reference(foreignKey = "sourceClientId", header = "注册客户端")
    private Dict sourceClient;

    @Column(name = "is_bbk", header = "是否八百客")
    private String isBbk = "0";

    @Column(name = "identity_card", header = "身份证号码")
    private String identityCard;

    @Column(name = "important", header = "402 重要程度: 4021普通、 4022中级、 4023高级、 4024VIP")
    private Important important = Important.COMMON;

    @Column(name = "company_id", header = "关联公司Id")
    private Integer companyId;

    @Reference(foreignKey = "companyId", header = "关联公司：默认最后一次关联", primaryKey = "pkid")
    private CompanyIntention company;

    @Column(name = "openid", header = "openid")
    private String openid;

    //-------------------非入库字段-----------------------
    @Exclusive
    @Column(name = "customer_name", header = "取'crm_customer'客户名称")
    private String customerName;

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

    public Dict getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(Dict sourceClient) {
        this.sourceClient = sourceClient;
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

    public Important getImportant() {
        return important;
    }

    public void setImportant(Important important) {
        this.important = important;
    }

    public CompanyIntention getCompany() {
        return company;
    }

    public void setCompany(CompanyIntention company) {
        this.company = company;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}