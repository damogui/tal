package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.baseinfo.AreaOne;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import com.gongsibao.entity.igirl.ic.dict.*;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_director",header = "董事")
public class IcDirector extends Entity{

    @Column(name = "member_id",header = "主要成员Id")
    private Integer  memberId;

    @Reference(foreignKey = "memberId",header = "主要成员")
    private IcMember member;

    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "name_eng",header = "姓名(英文)")
    private String nameEng;

    @Column(name = "cerType",header = "证件类型")
    private CorpLicenceType cerType;

    @Column(name = "cer_no",header = "证件号码")
    private String cerNo;

    @Column(name = "country",header = "国籍")
    private String country;

    @Column(name = "sex",header = "性别")
    private String sex;

    @Column(name = "mob_tel",header = "移动电话号码")
    private String mobTel;

    @Column(name = "liteDeg",header = "文化程度")
    private CorpEducationalLevel liteDeg;

    @Column(name = "nation",header = "民族")
    private CorpNation nation;

    @Column(name = "polStand",header = "政治面貌")
    private CorpPoliticalStatus polStand;

    @Column(name = "nat_date",header = "出生日期")
    private String natDate;

    @Column(name = "position",header = "职务")
    private CorpPosition position;

    @Column(name = "posBrForm",header = "产生方式")
    private CorpBuildMothed posBrForm;

    @Column(name = "offYears",header = "任职期限")
    private CorpDeadline offYears;

    @Column(name = "isManager",header = "是否兼任经理")
    private CorpBoolean isManager;

    @Column(name = "area_one_id",header = "户籍登记地址Id")
    private Integer areaOneId;

    @Reference(foreignKey = "areaOneId",header = "户籍登记地址")
    private AreaOne houseAddProv;

    @Column(name = "area_two_id",header = "户籍登记地址详细信息一Id")
    private Integer areaTwoId;

    @Reference(foreignKey = "areaTwoId",header = "户籍登记地址详细信息一")
    private AreaTwo houseAddCity;

    @Column(name = "house_add_other",header = "户籍登记地址详细信息二")
    private String houseAddOther;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public CorpLicenceType getCerType() {
        return cerType;
    }

    public void setCerType(CorpLicenceType cerType) {
        this.cerType = cerType;
    }

    public String getCerNo() {
        return cerNo;
    }

    public void setCerNo(String cerNo) {
        this.cerNo = cerNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobTel() {
        return mobTel;
    }

    public void setMobTel(String mobTel) {
        this.mobTel = mobTel;
    }

    public CorpEducationalLevel getLiteDeg() {
        return liteDeg;
    }

    public void setLiteDeg(CorpEducationalLevel liteDeg) {
        this.liteDeg = liteDeg;
    }

    public CorpNation getNation() {
        return nation;
    }

    public void setNation(CorpNation nation) {
        this.nation = nation;
    }

    public CorpPoliticalStatus getPolStand() {
        return polStand;
    }

    public void setPolStand(CorpPoliticalStatus polStand) {
        this.polStand = polStand;
    }

    public String getNatDate() {
        return natDate;
    }

    public void setNatDate(String natDate) {
        this.natDate = natDate;
    }

    public CorpPosition getPosition() {
        return position;
    }

    public void setPosition(CorpPosition position) {
        this.position = position;
    }

    public CorpBuildMothed getPosBrForm() {
        return posBrForm;
    }

    public void setPosBrForm(CorpBuildMothed posBrForm) {
        this.posBrForm = posBrForm;
    }

    public CorpDeadline getOffYears() {
        return offYears;
    }

    public void setOffYears(CorpDeadline offYears) {
        this.offYears = offYears;
    }

    public CorpBoolean getIsManager() {
        return isManager;
    }

    public void setIsManager(CorpBoolean isManager) {
        this.isManager = isManager;
    }

    public Integer getAreaOneId() {
        return areaOneId;
    }

    public void setAreaOneId(Integer areaOneId) {
        this.areaOneId = areaOneId;
    }

    public AreaOne getHouseAddProv() {
        return houseAddProv;
    }

    public void setHouseAddProv(AreaOne houseAddProv) {
        this.houseAddProv = houseAddProv;
    }

    public Integer getAreaTwoId() {
        return areaTwoId;
    }

    public void setAreaTwoId(Integer areaTwoId) {
        this.areaTwoId = areaTwoId;
    }

    public AreaTwo getHouseAddCity() {
        return houseAddCity;
    }

    public void setHouseAddCity(AreaTwo houseAddCity) {
        this.houseAddCity = houseAddCity;
    }

    public String getHouseAddOther() {
        return houseAddOther;
    }

    public void setHouseAddOther(String houseAddOther) {
        this.houseAddOther = houseAddOther;
    }
}
