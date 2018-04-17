package com.gongsibao.entity.igirl.ic;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.igirl.ic.baseinfo.*;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_register_case",header = "工商注册案子")
public class IcRegisterCase extends Entity{
    @Column(name = "hm_number",header = "核名文号")
    private String hmNumber;

    @Column(name = "hm_id",header = "核名身份证号")
    private String hmID;

    @Column(name = "company_name",header = "公司名称")
    private String companyName;

    @Column(name = "customer_id",header = "客户ID")
    private Integer customerId;

    @Reference(foreignKey = "customerId",header = "客户")
    private Customer customer;

    @Column(name = "baseInfo_id",header = "基础信息Id")
    private Integer baseInfoId;

    @Reference(foreignKey = "baseInfoId",header = "基础信息")
    private IcBaseInfo baseInfo;

    @Subs(foreignKey = "registerCaseId",header = "自然人股东",subType = IcPerson.class)
    private List<IcPerson> persons;

    @Subs(foreignKey = "registerCaseId",header = "非自然人股东",subType = IcUnPerson.class)
    private List<IcUnPerson> unPersons;

    @Column(name = "member_id",header = "主要成员信息Id")
    private Integer memberId;

    @Reference(foreignKey = "memberId",header = "主要成员信息")
    private IcMember member;

    @Column(name = "contact_id",header = "联系人Id")
    private Integer contactId;

    @Reference(foreignKey = "contactId",header = "联系人")
    private IcContact contact;

    @Subs(foreignKey = "fileUploadId",header = "文件上传",subType = IcFileUpload.class)
    private List<IcFileUpload> fileUploads;

    @Column(name = "extra_info_id",header = "补充信息Id")
    private Integer extraInfoId;

    @Reference(foreignKey = "extraInfoId",header = "补充信息")
    private IcExtraInfo extraInfo;

    @Column(name = "ent_ba_id",header = "备案信息id")
    private Integer entBaId;

    @Reference(foreignKey = "entBaId",header = "备案信息")
    private IcEntBa entBa;

    public String getHmNumber() {
        return hmNumber;
    }

    public void setHmNumber(String hmNumber) {
        this.hmNumber = hmNumber;
    }

    public String getHmID() {
        return hmID;
    }

    public void setHmID(String hmID) {
        this.hmID = hmID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(Integer baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public IcBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(IcBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public IcMember getMember() {
        return member;
    }

    public void setMember(IcMember member) {
        this.member = member;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public IcContact getContact() {
        return contact;
    }

    public void setContact(IcContact contact) {
        this.contact = contact;
    }

    public List<IcPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<IcPerson> persons) {
        this.persons = persons;
    }

    public List<IcUnPerson> getUnPersons() {
        return unPersons;
    }

    public void setUnPersons(List<IcUnPerson> unPersons) {
        this.unPersons = unPersons;
    }

    public List<IcFileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<IcFileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public Integer getExtraInfoId() {
        return extraInfoId;
    }

    public void setExtraInfoId(Integer extraInfoId) {
        this.extraInfoId = extraInfoId;
    }

    public IcExtraInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(IcExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Integer getEntBaId() {
        return entBaId;
    }

    public void setEntBaId(Integer entBaId) {
        this.entBaId = entBaId;
    }

    public IcEntBa getEntBa() {
        return entBa;
    }

    public void setEntBa(IcEntBa entBa) {
        this.entBa = entBa;
    }
}
