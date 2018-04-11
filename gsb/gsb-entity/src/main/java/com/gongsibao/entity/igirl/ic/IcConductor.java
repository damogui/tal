package com.gongsibao.entity.igirl.ic;

import com.gongsibao.entity.crm.dic.Sex;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaOne;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import com.gongsibao.entity.igirl.ic.baseinfo.Nationality;
import com.gongsibao.entity.igirl.ic.dict.*;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;

public class IcConductor {

    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "name_eng",header = "姓名(英文)")
    private String nameEng;

    @Column(name = "cer_type",header = "证件类型")
    private CorpLicenceType cerType;

    @Column(name = "cer_no",header = "证件号码")
    private String cerNo;

    @Column(name = "country",header = "国籍")
    private Nationality country;

    @Column(name = "sex",header = "性别")
    private Sex sex;

    @Column(name = "mob_tel",header = "移动电话号码")
    private String mobTel;

    @Column(name = "lite_deg",header = "文化程度")
    private CorpEducationalLevel liteDeg;

    @Column(name = "nation",header = "民族")
    private CorpNation nation;

    @Column(name = "pol_stand",header = "政治面貌")
    private CorpPoliticalStatus polStand;

    @Column(name = "nat_date",header = "出生日期")
    private String natDate;

    @Column(name = "pos_br_form",header = "产生方式")
    private CorpBuildMothed posBrForm;

    @Column(name = "off_years",header = "任职期限")
    private CorpDeadline offYears;

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

    @Column(name = "sups_type",header = "监事类型")
    private CorpSupervisor supsType;

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

    public Nationality getCountry() {
        return country;
    }

    public void setCountry(Nationality country) {
        this.country = country;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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

    public CorpSupervisor getSupsType() {
        return supsType;
    }

    public void setSupsType(CorpSupervisor supsType) {
        this.supsType = supsType;
    }
}
