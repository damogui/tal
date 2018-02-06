package com.gongsibao.igirl.dto.TradeMark;


import org.netsharp.util.StringManager;

//申请人信息
public class Step2 {
    //代理文号,输入框
    private String agentFilenum;

    //代理人姓名,输入框
    private String agentPerson;

    //代理委托书图片全URL地址，
    private String agentBookPath;

    //代理委托书文件名，不含地址，含扩展名
    private String agentBookName;

    //统一社会信用代码,输入框
    private String certCode;

    //申请人名称，输入框
    private String appCnName;

    //申请人地址,输入框
    private String appCnAddr;

    //联系人,输入框
    private String appContactPerson;

    //联系电话，输入框
    private String appContactTel;

    //传真（含地区号）,输入框
    private String appContactFax;

    //邮政编码,输入框
    private String appContactZip;

    //主体资格证明文件
    private String certFilePath;

    private String certFileName;

    //证件名称
    private String appCertificateId;
    //证件号码
    private String appCertificateNum;

    //身份证明文件
    private String appCertFilePath;

    private String appCertFileName;

    public String getAgentFilenum() {
        return agentFilenum;
    }

    public void setAgentFilenum(String agentFilenum) {
        this.agentFilenum = agentFilenum;
    }

    public String getAgentPerson() {
        return agentPerson;
    }

    public void setAgentPerson(String agentPerson) {
        this.agentPerson = agentPerson;
    }

    public String getAgentBookPath() {
        return agentBookPath;
    }

    public void setAgentBookPath(String agentBookPath) {
        this.agentBookPath = agentBookPath;
    }

    public String getAgentBookName() {
        return agentBookName;
    }

    public void setAgentBookName(String agentBookName) {
        this.agentBookName = agentBookName;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getAppCnName() {
        return appCnName;
    }

    public void setAppCnName(String appCnName) {
        this.appCnName = appCnName;
    }

    public String getAppCnAddr() {
        return appCnAddr;
    }

    public void setAppCnAddr(String appCnAddr) {
        this.appCnAddr = appCnAddr;
    }

    public String getAppContactPerson() {
        return appContactPerson;
    }

    public void setAppContactPerson(String appContactPerson) {
        this.appContactPerson = appContactPerson;
    }

    public String getAppContactTel() {
        return appContactTel;
    }

    public void setAppContactTel(String appContactTel) {
        this.appContactTel = appContactTel;
    }

    public String getAppContactFax() {
        return appContactFax;
    }

    public void setAppContactFax(String appContactFax) {
        this.appContactFax = appContactFax;
    }

    public String getAppContactZip() {
        return appContactZip;
    }

    public void setAppContactZip(String appContactZip) {
        this.appContactZip = appContactZip;
    }

    public String getCertFilePath() {
        return certFilePath;
    }

    public void setCertFilePath(String certFilePath) {
        this.certFilePath = certFilePath;
    }

    public String getCertFileName() {
        return certFileName;
    }

    public void setCertFileName(String certFileName) {
        this.certFileName = certFileName;
    }

    public String getAppCertificateId() {
        return StringManager.isNullOrEmpty(appCertificateId)?"":appCertificateId;
    }

    public void setAppCertificateId(String appCertificateId) {
        this.appCertificateId = appCertificateId;
    }

    public String getAppCertificateNum() {
        return StringManager.isNullOrEmpty(appCertificateNum)?"":appCertificateNum;
    }

    public void setAppCertificateNum(String appCertificateNum) {
        this.appCertificateNum = appCertificateNum;
    }

    public String getAppCertFilePath() {
        return StringManager.isNullOrEmpty(appCertFilePath)?"":appCertFilePath;
    }

    public void setAppCertFilePath(String appCertFilePath) {
        this.appCertFilePath = appCertFilePath;
    }

    public String getAppCertFileName() {
        return StringManager.isNullOrEmpty(appCertFileName)?"":appCertFileName;
    }

    public void setAppCertFileName(String appCertFileName) {
        this.appCertFileName = appCertFileName;
    }
}
