package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpEducationalLevel;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.MemberType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_member",header = "内部成员")
public class Member extends Entity {
    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "mobile",header = "手机")
    private String mobile;

    @Column(name = "telephone",header = "固定电话")
    private String telephone;

    @Column(name = "address",header = "住宅地址")
    private String address;

    @Column(name = "identify",header = "身份证号")
    private String identify;

    @Column(name = "id_address",header = "身份证住址")
    private String idAddress;

    @Column(name = "education",header = "学历")
    private CorpEducationalLevel education;

    @Column(name = "email",header = "邮箱地址")
    private String email;

    @Column(name = "ic_ex_base_info_id",header = "基础内容ID")
    private Integer excelBaseInfoId;

    @JsonIgnore
    @Reference(foreignKey = "excelBaseInfoId",header = "基础内容")
    private ExcelBaseInfo excelBaseInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CorpEducationalLevel getEducation() {
        return education;
    }

    public void setEducation(CorpEducationalLevel education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
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
}
