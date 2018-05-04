package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpLicenceType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_business",header = "企业联系人")
public class IcBusiness extends Entity{
    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "cer_type",header = "证件类型")
    private CorpLicenceType cerType;

    @Column(name = "cer_no",header = "证件号码")
    private String cerNo;

    @Column(name = "mobile",header = "移动电话号码")
    private String mobile;

    @Column(name = "tel",header = "固定电话")
    private String tel;

    @Column(name = "email",header = "电子邮件")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
