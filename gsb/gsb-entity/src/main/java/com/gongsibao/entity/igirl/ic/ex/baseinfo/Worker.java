package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.ex.dict.MemberType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_worker",header = "企业职员")
public class Worker extends Entity {
    @Column(name = "ic_ex_member_name",header = "成员名称")
    private String memberName;

    @Column(name = "ic_ex_member_mobile",header = "成员电话")
    private String memberMobile;

    @Column(name = "position",header = "职位")
    private MemberType position;

    @Column(name = "isNeed",header = "是否完善")
    private boolean isNeed = false;

    @Column(name = "ic_ex_base_info_id",header = "基础内容ID")
    private Integer excelBaseInfoId;

    @JsonIgnore
    @Reference(foreignKey = "excelBaseInfoId",header = "基础内容")
    private ExcelBaseInfo excelBaseInfo;

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

    public MemberType getPosition() {
        return position;
    }

    public void setPosition(MemberType position) {
        this.position = position;
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

    public boolean isNeed() {
        return isNeed;
    }

    public void setNeed(boolean need) {
        isNeed = need;
    }
}
