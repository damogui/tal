package com.gongsibao.entity.igirl;

import com.gongsibao.entity.igirl.dict.*;
import com.gongsibao.entity.supplier.Supplier;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_transfer_trade_mark",header = "商标转让")
public class TransferTradeMark extends Entity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1032124897710059378L;
	@Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = -1;
    @JsonIgnore
    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;
    @Column(name = "department_id",header = "部门ID")
    private Integer departmentId;

    @Column(name = "transfer_type",header = "选择办理业务")
    private TransferType transferType = TransferType.TRANSFER;

    @Column(name = "cert_code",header = "统一社会信用代码")
    private String certCode;

    @Column(name = "assigneeGjdq",header = "转让人国籍")
    private WriteType assigneeGjdq = WriteType.DALU;

    @Column(name = "appCrtyId",header = "转让人国家或地区")
    private String appCrtyId="";

    @Column(name = "assignee_cn_name",header = "转让人名称(中文)")
    private String assigneeCnName;

    @Column(name = "assignee_en_name",header = "转让人名称(英文)")
    private String assigneeEnName;

    @Column(name = "assignee_cn_addr",header = "转让人地址(中文)")
    private String assigneeCnAddr;

    @Column(name = "assignee_en_addr",header = "转让人地址(英文)")
    private String assigneeEnAddr;

    @Column(name = "appGjdq",header = "受让人国籍")
    private WriteType appGjdq=WriteType.DALU;

    @Column(name = "assignor_crty_id",header = "受让人国家或地区")
    private String assignorCrtyId="";

    @Column(name = "assignor_cn_name",header = "受让人名称(中文)")
    private String assignorCnName;

    @Column(name = "assignor_en_name",header = "受让人名称(英文)")
    private String assignorEnName;

    @Column(name = "assignor_cn_addr",header = "受让人地址(中文)")
    private String assignorCnAddr;

    @Column(name = "assignor_en_addr",header = "受让人地址(英文)")
    private String assignorEnAddr;

    @Column(name = "accept_person",header = "国内接收人")
    private String acceptPerson="";

    @Column(name = "accept_addr",header = "国内接收人地址")
    private String acceptAddr="";

    @Column(name = "accept_zip",header = "国内接收人邮政编码")
    private String acceptZip="";

    @Column(name = "assignee_contact_zip",header = "邮政编码")
    private String assigneeContactZip;

    @Column(name = "assignee_contact_person",header = "联系人")
    private String assigneeContactPerson;

    @Column(name = "assignee_contact_tel",header = "联系电话")
    private String assigneeContactTel;

    @Column(name = "agent_file_num",header = "代理文号")
    private String agentFileNum;

    @Column(name = "agent_person",header = "代理人姓名")
    private String agentPerson;

    @Column(name = "file_wt_name",header = "转让人委托书",size = 255)
    private String fileWtName;

    @Column(name = "file_wt_name2",header = "受让人委托书",size = 255)
    private String fileWtName2;

    @Column(name = "zdllx",header = "转让人类型")
    private ChangeApplierType zdllx = ChangeApplierType.PUBLIC;

    @Column(name = "sdjlx",header = "受让人类型")
    private ChangeApplierType sdjlx = ChangeApplierType.PUBLIC;

    @Column(name = "zwjlx",header = "转让人上传文件的语言类型")
    private LanguageType zwjlx = LanguageType.CHINESE;

    @Column(name = "swjlx",header = "受让人上传文件的语言类型")
    private LanguageType swjlx = LanguageType.CHINESE;

    @Column(name = "gqcx_name",header = "自然人死亡/企业或其他组织注销证明",size = 255)
    private String gqcxName;

    @Column(name = "zr_zt_cn_tr_name",header = "转让人主体资格证明文件(中文)",size = 255)
    private String zrZtCnTrName;

    @Column(name = "zr_zt_en_tr_name",header = "转让人主体资格证明文件(外文)",size = 255)
    private String zrZtEnTrName;

    @Column(name = "sr_zt_cn_tr_name",header = "受让人主体资格证明文件(中文)",size = 255)
    private String srZtCnTrName;

    @Column(name = "sr_zt_en_tr_name",header = "受让人主体资格证明文件(外文)",size = 255)
    private String srZtEnTrName;

    @Column(name = "cert_type",header = "转让人证件名称")
    private CertificateType certType;

    @Column(name = "cert_no",header = "转让人证件号码")
    private String certNo;

    @Column(name = "scert_type",header = "受让人证件名称")
    private CertificateType scertType;

    @Column(name = "scert_no",header = "受让人证件号码")
    private String scertNo;

    @Column(name = "zr_sf_cn_tr_name",header = "转让人身份证明文件(中文)",size = 255)
    private String zrSfCnTrName;

    @Column(name = "sr_sf_cn_tr_name",header = "受让人身份证明文件(中文)",size = 255)
    private String srSfCnTrName;

    @Column(name = "zr_sf_en_tr_name",header = "转让人身份证明文件(外文)",size = 255)
    private String zrSfEnTrName;

    @Column(name = "sr_sf_en_tr_name",header = "受让人身份证明文件(外文)",size = 255)
    private String srSfEnTrName;

    @Column(name = "flws_name",header = "同意转让声明或商标移转证明",size = 255)
    private String flwsName;

    @Column(name = "tm_type",header = "选择商标类型")
    private ChangeTradeMarkType tmType=ChangeTradeMarkType.GENERAL;

    @Column(name = "if_share_tm",header = "是否含转/受让共有人")
    private String ifShareTm = "ifShareTm2";

    @Column(name = "apporreg_num",header = "商标注册号")
    private String apporregNum;

    @Column(name = "file_yg_name",header = "有关说明文件",size = 255)
    private String fileYgName;

    @Column(name = "transfer_trade_mark_state",header = "商标转移状态")
    private TransferTradeMarkState transferTradeMarkState = TransferTradeMarkState.WAIT_CONFIRM;

    @Column(name = "owner_id",header = "所属者ID")
    private Integer ownerId;

    @Column(name = "owner_name",header = "所属者名称")
    private String ownerName;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public WriteType getAssigneeGjdq() {
        return assigneeGjdq;
    }

    public void setAssigneeGjdq(WriteType assigneeGjdq) {
        this.assigneeGjdq = assigneeGjdq;
    }

    public String getAppCrtyId() {
        return appCrtyId;
    }

    public void setAppCrtyId(String appCrtyId) {
        this.appCrtyId = appCrtyId;
    }

    public String getAssigneeCnName() {
        return assigneeCnName;
    }

    public void setAssigneeCnName(String assigneeCnName) {
        this.assigneeCnName = assigneeCnName;
    }

    public String getAssigneeEnName() {
        return assigneeEnName;
    }

    public void setAssigneeEnName(String assigneeEnName) {
        this.assigneeEnName = assigneeEnName;
    }

    public String getAssigneeCnAddr() {
        return assigneeCnAddr;
    }

    public void setAssigneeCnAddr(String assigneeCnAddr) {
        this.assigneeCnAddr = assigneeCnAddr;
    }

    public String getAssigneeEnAddr() {
        return assigneeEnAddr;
    }

    public void setAssigneeEnAddr(String assigneeEnAddr) {
        this.assigneeEnAddr = assigneeEnAddr;
    }

    public WriteType getAppGjdq() {
        return appGjdq;
    }

    public void setAppGjdq(WriteType appGjdq) {
        this.appGjdq = appGjdq;
    }

    public String getAssignorCrtyId() {
        return assignorCrtyId;
    }

    public void setAssignorCrtyId(String assignorCrtyId) {
        this.assignorCrtyId = assignorCrtyId;
    }

    public String getAssignorCnName() {
        return assignorCnName;
    }

    public void setAssignorCnName(String assignorCnName) {
        this.assignorCnName = assignorCnName;
    }

    public String getAssignorEnName() {
        return assignorEnName;
    }

    public void setAssignorEnName(String assignorEnName) {
        this.assignorEnName = assignorEnName;
    }

    public String getAssignorCnAddr() {
        return assignorCnAddr;
    }

    public void setAssignorCnAddr(String assignorCnAddr) {
        this.assignorCnAddr = assignorCnAddr;
    }

    public String getAssignorEnAddr() {
        return assignorEnAddr;
    }

    public void setAssignorEnAddr(String assignorEnAddr) {
        this.assignorEnAddr = assignorEnAddr;
    }

    public String getAcceptPerson() {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson;
    }

    public String getAcceptAddr() {
        return acceptAddr;
    }

    public void setAcceptAddr(String acceptAddr) {
        this.acceptAddr = acceptAddr;
    }

    public String getAcceptZip() {
        return acceptZip;
    }

    public void setAcceptZip(String acceptZip) {
        this.acceptZip = acceptZip;
    }

    public String getAssigneeContactZip() {
        return assigneeContactZip;
    }

    public void setAssigneeContactZip(String assigneeContactZip) {
        this.assigneeContactZip = assigneeContactZip;
    }

    public String getAssigneeContactPerson() {
        return assigneeContactPerson;
    }

    public void setAssigneeContactPerson(String assigneeContactPerson) {
        this.assigneeContactPerson = assigneeContactPerson;
    }

    public String getAssigneeContactTel() {
        return assigneeContactTel;
    }

    public void setAssigneeContactTel(String assigneeContactTel) {
        this.assigneeContactTel = assigneeContactTel;
    }

    public String getAgentFileNum() {
        return agentFileNum;
    }

    public void setAgentFileNum(String agentFileNum) {
        this.agentFileNum = agentFileNum;
    }

    public String getAgentPerson() {
        return agentPerson;
    }

    public void setAgentPerson(String agentPerson) {
        this.agentPerson = agentPerson;
    }

    public String getFileWtName() {
        return fileWtName;
    }

    public void setFileWtName(String fileWtName) {
        this.fileWtName = fileWtName;
    }

    public String getFileWtName2() {
        return fileWtName2;
    }

    public void setFileWtName2(String fileWtName2) {
        this.fileWtName2 = fileWtName2;
    }

    public ChangeApplierType getZdllx() {
        return zdllx;
    }

    public void setZdllx(ChangeApplierType zdllx) {
        this.zdllx = zdllx;
    }

    public ChangeApplierType getSdjlx() {
        return sdjlx;
    }

    public void setSdjlx(ChangeApplierType sdjlx) {
        this.sdjlx = sdjlx;
    }

    public LanguageType getZwjlx() {
        return zwjlx;
    }

    public void setZwjlx(LanguageType zwjlx) {
        this.zwjlx = zwjlx;
    }

    public LanguageType getSwjlx() {
        return swjlx;
    }

    public void setSwjlx(LanguageType swjlx) {
        this.swjlx = swjlx;
    }

    public String getGqcxName() {
        return gqcxName;
    }

    public void setGqcxName(String gqcxName) {
        this.gqcxName = gqcxName;
    }

    public String getZrZtCnTrName() {
        return zrZtCnTrName;
    }

    public void setZrZtCnTrName(String zrZtCnTrName) {
        this.zrZtCnTrName = zrZtCnTrName;
    }

    public String getZrZtEnTrName() {
        return zrZtEnTrName;
    }

    public void setZrZtEnTrName(String zrZtEnTrName) {
        this.zrZtEnTrName = zrZtEnTrName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getScertNo() {
        return scertNo;
    }

    public void setScertNo(String scertNo) {
        this.scertNo = scertNo;
    }

    public String getZrSfCnTrName() {
        return zrSfCnTrName;
    }

    public void setZrSfCnTrName(String zrSfCnTrName) {
        this.zrSfCnTrName = zrSfCnTrName;
    }

    public String getSrSfCnTrName() {
        return srSfCnTrName;
    }

    public void setSrSfCnTrName(String srSfCnTrName) {
        this.srSfCnTrName = srSfCnTrName;
    }

    public String getZrSfEnTrName() {
        return zrSfEnTrName;
    }

    public void setZrSfEnTrName(String zrSfEnTrName) {
        this.zrSfEnTrName = zrSfEnTrName;
    }

    public String getSrSfEnTrName() {
        return srSfEnTrName;
    }

    public void setSrSfEnTrName(String srSfEnTrName) {
        this.srSfEnTrName = srSfEnTrName;
    }

    public String getFlwsName() {
        return flwsName;
    }

    public void setFlwsName(String flwsName) {
        this.flwsName = flwsName;
    }

    public ChangeTradeMarkType getTmType() {
        return tmType;
    }

    public void setTmType(ChangeTradeMarkType tmType) {
        this.tmType = tmType;
    }

    public String getIfShareTm() {
        return ifShareTm;
    }

    public void setIfShareTm(String ifShareTm) {
        this.ifShareTm = ifShareTm;
    }

    public String getApporregNum() {
        return apporregNum;
    }

    public void setApporregNum(String apporregNum) {
        this.apporregNum = apporregNum;
    }

    public String getFileYgName() {
        return fileYgName;
    }

    public void setFileYgName(String fileYgName) {
        this.fileYgName = fileYgName;
    }

    public TransferTradeMarkState getTransferTradeMarkState() {
        return transferTradeMarkState;
    }

    public void setTransferTradeMarkState(TransferTradeMarkState transferTradeMarkState) {
        this.transferTradeMarkState = transferTradeMarkState;
    }

    public CertificateType getCertType() {
        return certType;
    }

    public void setCertType(CertificateType certType) {
        this.certType = certType;
    }

    public CertificateType getScertType() {
        return scertType;
    }

    public void setScertType(CertificateType scertType) {
        this.scertType = scertType;
    }

    public String getSrZtCnTrName() {
        return srZtCnTrName;
    }

    public void setSrZtCnTrName(String srZtCnTrName) {
        this.srZtCnTrName = srZtCnTrName;
    }

    public String getSrZtEnTrName() {
        return srZtEnTrName;
    }

    public void setSrZtEnTrName(String srZtEnTrName) {
        this.srZtEnTrName = srZtEnTrName;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
