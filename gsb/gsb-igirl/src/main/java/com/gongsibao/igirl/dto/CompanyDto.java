package com.gongsibao.igirl.dto;

public class CompanyDto {
    //公司名称
    private String appCnName;

    //统一信用代码
    private String certCode;

    //公司地址
    private String appCnAddr;

    //法人
    private String applyer;

    //邮编
    private String postcode;

    //传真
    private String fax;

    public String getAppCnName() {
        return appCnName;
    }

    public void setAppCnName(String appCnName) {
        this.appCnName = appCnName;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getAppCnAddr() {
        return appCnAddr;
    }

    public void setAppCnAddr(String appCnAddr) {
        this.appCnAddr = appCnAddr;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
