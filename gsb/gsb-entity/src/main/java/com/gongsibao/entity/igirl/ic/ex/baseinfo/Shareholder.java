package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_shareholder",header = "股东信息")
public class Shareholder extends Entity {
    @Column(name = "name",header = "股东姓名")
    private String name;

    @Column(name = "amount",header = "出资金额")
    private String amount;

    @Column(name = "ratio",header = "出资比例")
    private String ratio;

    @Column(name = "mobile",header = "手机")
    private String mobile;

    @Column(name = "email",header = "邮箱")
    private String email;

    @Column(name = "identify",header = "身份证号")
    private String identify;

    @Column(name = "address",header = "身份证住址")
    private String address;

    @Column(name = "ic_ex_register_case_id",header = "信息登记表ID")
    private Integer icExRegisterCaseId;

    @Reference(foreignKey = "icExRegisterCaseId",header = "信息登记表")
    private IcExRegisterCase icExRegisterCase;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
