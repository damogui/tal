package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_corporate_address",header = "企业住址")
public class CorporateAddress extends Entity{
    @Column(name = "own_land_type",header = "是否为自有地")
    private boolean ownLandType = true;

    @Column(name = "own_land_address",header = "自有地址")
    private String ownLandAddress;

    @Column(name = "land_type",header = "住所情况")
    private String landType;

    @Column(name = "land_area",header = "房屋面积")
    private String landArea;

    @Column(name = "is_belong_west_district",header = "是否属于中关村西区管辖")
    private BooleanType isBelongWestDistrict = BooleanType.NO;

    @Column(name = "land_owner",header = "房屋产权人")
    private String landOwner;

    @Column(name = "street_office",header = "街道办事处")
    private String streetOffice;

    @Column(name = "our_address",header = "提供地址")
    private String ourAddress;

    @Column(name = "word_address",header = "实际办公地址")
    private String workAddress;

    @Column(name = "state",header = "状态")
    private boolean state = false;

    @Column(name = "ic_ex_base_info_id",header = "基础内容ID")
    private Integer excelBaseInfoId;

    @JsonIgnore
    @Reference(foreignKey = "excelBaseInfoId",header = "基础内容")
    private ExcelBaseInfo excelBaseInfo;

    public boolean isOwnLandType() {
        return ownLandType;
    }

    public void setOwnLandType(boolean ownLandType) {
        this.ownLandType = ownLandType;
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

    public String getOwnLandAddress() {
        return ownLandAddress;
    }

    public void setOwnLandAddress(String ownLandAddress) {
        this.ownLandAddress = ownLandAddress;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public BooleanType getIsBelongWestDistrict() {
        return isBelongWestDistrict;
    }

    public void setIsBelongWestDistrict(BooleanType isBelongWestDistrict) {
        this.isBelongWestDistrict = isBelongWestDistrict;
    }

    public String getLandOwner() {
        return landOwner;
    }

    public void setLandOwner(String landOwner) {
        this.landOwner = landOwner;
    }

    public String getStreetOffice() {
        return streetOffice;
    }

    public void setStreetOffice(String streetOffice) {
        this.streetOffice = streetOffice;
    }

    public String getOurAddress() {
        return ourAddress;
    }

    public void setOurAddress(String ourAddress) {
        this.ourAddress = ourAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
