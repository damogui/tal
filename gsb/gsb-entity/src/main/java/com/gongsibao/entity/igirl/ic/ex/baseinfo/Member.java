package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpEducationalLevel;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.MemberType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_member",header = "内部成员")
public class Member extends Entity {
    @Column(name = "type",header = "成员类型")
    private MemberType type;

    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "mobile",header = "手机")
    private String mobile;

    @Column(name = "telephone",header = "固定电话")
    private String telephone;

    @Column(name = "address",header = "住宅地址")
    private String address;

    @Column(name = "education",header = "学历")
    private CorpEducationalLevel education;

    @Column(name = "email",header = "邮箱地址")
    private String email;

    @Column(name = "ic_ex_register_case_id",header = "信息登记表ID")
    private Integer icExRegisterCaseId;

    @Reference(foreignKey = "icExRegisterCaseId",header = "信息登记表")
    private IcExRegisterCase icExRegisterCase;


    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

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

    public Integer getIcExRegisterCaseId() {
        return icExRegisterCaseId;
    }

    public void setIcExRegisterCaseId(Integer icExRegisterCaseId) {
        this.icExRegisterCaseId = icExRegisterCaseId;
    }

    public IcExRegisterCase getIcExRegisterCase() {
        return icExRegisterCase;
    }

    public void setIcExRegisterCase(IcExRegisterCase icExRegisterCase) {
        this.icExRegisterCase = icExRegisterCase;
    }
}
