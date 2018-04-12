package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.dict.CorpLicenceType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_finance",header = "财务负责人")
public class IcFinance extends Entity{
    @Column(name = "name",header = "姓名")
    private String name;

    @Column(name = "cer_type",header = "中国人民共和国居民身份证")
    private CorpLicenceType cerType;

    @Column(name = "cer_no",header = "证件号码")
    private String cerNo;

    @Column(name = "mobile",header = "移动电话号码")
    private String mobile;

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
}
