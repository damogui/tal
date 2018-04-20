package com.gongsibao.entity.igirl.ic.baseinfo;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.Date;

@Table(name="ig_base_chapter_two",header="注册刻章公司",orderBy="code asc")
public class ChapterTwo extends Entity {

    @Column(name="code",header="企业代码")
    private String code;

    @Column(name="name",header="企业名称")
    private String name;

    @Column(name="arn_code",header="信用代码")
    private String arnCode;

    @Column(name="special_code",header="特行代码")
    private String specialCode;

    @Column(name="address",size = 256,header="经营地址")
    private String address;

    @Column(name="operation_content",size = 4096,header="经营范围")
    private String operationContent;

    @Column(name="tel",header="联系电话")
    private String tel;

    @Column(name="fax",header="传真")
    private String fax;

    @Column(name="email",header="email")
    private String email;

    @Column(name="registered_capital",header="注册资本")
    private String registeredCapital;

    @Column(name="set_up_time",header="设立时间")
    private String setUpTime;

    @Column(name="secure_man",header="安全员")
    private String secureMan;

    @Column(name="police_man",header="民警")
    private String policeMan;

    @Column(name="operation_area",header="经营面积")
    private String operationArea;

    @Column(name="artificial_person_name",header="紧急联系人")
    private String artificialPersonName;

    @Column(name="artificial_persion_mobile",header="紧急联系人手机")
    private String artificialPersionMobile;

    @Column(name="artificial_persion_idcard",header="紧急联系人身份证号")
    private String artificialPersionIdcard;

    @Column(name="artificial_persion_address",header="紧急联系人住址")
    private String artificialPersionAddress;

    @Column(name="artificialPersion_cert_type",header="紧急联系人确认状态")
    private String artificialPersionCertType;

    @Column(name="area",header="地区ID")
    private String area;

    @Column(name = "chaper_one_id",header = "ChapterOne外键")
    private Integer chaperOneId;

    @Reference(foreignKey="chaperOneId",header="地区")
    private ChapterOne chapterOne;

	@Column(name="sid",header="原数据ID")
    private Integer sid;

    @Column(name="police_code",header="原policeCode")
    private Integer policeCode;

    @Column(name="user",header="原user")
    private Integer user;

    @Column(name="police_station",header="原policeStation")
    private Integer policeStation;

    @Column(name="factory_status",header="原factoryStatus")
    private Integer factoryStatus;


    @Column(name="arn_pic",size=256,header="arnPic")
    private String arnPic;


    @Column(name="special_pic",size=256,header="specialPic")
    private String specialPic;


    @Column(name="addrimg_pic",size=256,header="addrimgPic")
    private String addrimgPic;


    @Column(name="company_pic",size=256,header="companyPic")
    private String companyPic;

    @Column(name="sgmj",size=256,header="sgmj")
    private String sgmj;

    @Column(name="sjgj",size=256,header="sjgj")
    private String sjgj;

    @Column(name="sswj",size=256,header="sswj")
    private Integer sswj;

    @Column(name="version",header="version")
    private String version;

    @Column(name="created_time",header="createdTime")
    private String createdTime;


    @Column(name="updated_time",header="updatedTime")
    private String updatedTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArnCode() {
        return arnCode;
    }

    public void setArnCode(String arnCode) {
        this.arnCode = arnCode;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getSetUpTime() {
        return setUpTime;
    }

    public void setSetUpTime(String setUpTime) {
        this.setUpTime = setUpTime;
    }

    public String getSecureMan() {
        return secureMan;
    }

    public void setSecureMan(String secureMan) {
        this.secureMan = secureMan;
    }

    public String getPoliceMan() {
        return policeMan;
    }

    public void setPoliceMan(String policeMan) {
        this.policeMan = policeMan;
    }

    public String getOperationArea() {
        return operationArea;
    }

    public void setOperationArea(String operationArea) {
        this.operationArea = operationArea;
    }

    public String getArtificialPersonName() {
        return artificialPersonName;
    }

    public void setArtificialPersonName(String artificialPersonName) {
        this.artificialPersonName = artificialPersonName;
    }

    public String getArtificialPersionMobile() {
        return artificialPersionMobile;
    }

    public void setArtificialPersionMobile(String artificialPersionMobile) {
        this.artificialPersionMobile = artificialPersionMobile;
    }

    public String getArtificialPersionIdcard() {
        return artificialPersionIdcard;
    }

    public void setArtificialPersionIdcard(String artificialPersionIdcard) {
        this.artificialPersionIdcard = artificialPersionIdcard;
    }

    public String getArtificialPersionAddress() {
        return artificialPersionAddress;
    }

    public void setArtificialPersionAddress(String artificialPersionAddress) {
        this.artificialPersionAddress = artificialPersionAddress;
    }

    public String getArtificialPersionCertType() {
        return artificialPersionCertType;
    }

    public void setArtificialPersionCertType(String artificialPersionCertType) {
        this.artificialPersionCertType = artificialPersionCertType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getChaperOneId() {
        return chaperOneId;
    }

    public void setChaperOneId(Integer chaperOneId) {
        this.chaperOneId = chaperOneId;
    }

    public ChapterOne getChapterOne() {
        return chapterOne;
    }

    public void setChapterOne(ChapterOne chapterOne) {
        this.chapterOne = chapterOne;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getPoliceCode() {
        return policeCode;
    }

    public void setPoliceCode(Integer policeCode) {
        this.policeCode = policeCode;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(Integer policeStation) {
        this.policeStation = policeStation;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public String getArnPic() {
        return arnPic;
    }

    public void setArnPic(String arnPic) {
        this.arnPic = arnPic;
    }

    public String getSpecialPic() {
        return specialPic;
    }

    public void setSpecialPic(String specialPic) {
        this.specialPic = specialPic;
    }

    public String getAddrimgPic() {
        return addrimgPic;
    }

    public void setAddrimgPic(String addrimgPic) {
        this.addrimgPic = addrimgPic;
    }

    public String getCompanyPic() {
        return companyPic;
    }

    public void setCompanyPic(String companyPic) {
        this.companyPic = companyPic;
    }

    public String getSgmj() {
        return sgmj;
    }

    public void setSgmj(String sgmj) {
        this.sgmj = sgmj;
    }

    public String getSjgj() {
        return sjgj;
    }

    public void setSjgj(String sjgj) {
        this.sjgj = sjgj;
    }

    public Integer getSswj() {
        return sswj;
    }

    public void setSswj(Integer sswj) {
        this.sswj = sswj;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}