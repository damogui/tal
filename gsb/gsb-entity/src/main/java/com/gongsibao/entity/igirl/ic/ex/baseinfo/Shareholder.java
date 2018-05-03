package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_shareholder",header = "股东信息")
public class Shareholder extends Entity {
    @Column(name = "ic_ex_member_name",header = "成员名称")
    private String memberName;

    @Column(name = "ic_ex_member_mobile",header = "成员电话")
    private String memberMobile;

    @Column(name = "amount",header = "出资金额")
    private String amount;

    @Column(name = "ratio",header = "出资比例")
    private String ratio;

    @Column(name = "ic_ex_base_info_id",header = "基础内容ID")
    private Integer excelBaseInfoId;

    @JsonIgnore
    @Reference(foreignKey = "excelBaseInfoId",header = "基础内容")
    private ExcelBaseInfo excelBaseInfo;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
}
