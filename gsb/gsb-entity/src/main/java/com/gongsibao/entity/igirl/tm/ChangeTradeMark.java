package com.gongsibao.entity.igirl.tm;

import com.gongsibao.entity.igirl.tm.dict.*;
import com.gongsibao.entity.supplier.Supplier;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_change_trade_mark",header = "商标变更")
public class ChangeTradeMark extends Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6120929894850003833L;
	@Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = -1;
    @JsonIgnore
    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Column(name = "write_type", header = "申请人国籍")
    private WriteType writeType = WriteType.DALU;

    @Column(name = "cert_code",header = "统一社会信用代码")
    private String certCode;

    @Column(name = "change_type",header = "变更类型")
    private ChangeType changeType = ChangeType.CHANGE_MD;

    @Column(name = "txt_sqrmyzw",header = "申请人名称(中文)")
    private String txt_sqrmyzw;

    @Column(name = "txt_sqrmyyw",header = "申请人名称(英文)")
    private String txt_sqrmyyw;

    @Column(name = "txt_sqrdzzw",header = "申请人地址(中文)")
    private String txt_sqrdzzw;

    @Column(name = "txt_sqrdzyw",header = "申请人地址(英文)")
    private String txt_sqrdzyw;

    @Column(name = "txt_yzbm",header = "邮政编码")
    private String txt_yzbm;

    @Column(name = "txt_lxr",header = "联系人")
    private String txt_lxr;

    @Column(name = "txt_dh",header = "联系电话")
    private String txt_dh;

    @Column(name = "agent_file_num",header = "代理文号")
    private String agentFileNum;

    @Column(name = "txt_dlrmc",header = "代理人姓名")
    private String txt_dlrmc;

    @Column(name = "agent_book_path",header = "代理委托书",size = 255)
    private String agentBookPath;

    @Column(name = "applier_type",header = "申请人类型")
    private ChangeApplierType applierType = ChangeApplierType.PUBLIC;

    @Column(name = "language_type",header = "语言类型")
    private LanguageType languageType = LanguageType.CHINESE;

    @Column(name = "cert_file_path",header = "主体资格证明文件(中文)",size = 255)
    private String certFilePath;

    @Column(name = "cert_file_en_path",header = "主体资格证明原文件(外文)",size = 255)
    private String certFileENPath;

    @Column(name = "certificate_type",header = "证件名称")
    private CertificateType certificateType;

    @Column(name = "app_certificate_num",header = "证件号码")
    private String appCertificateNum;

    @Column(name = "app_cert_file_path",header = "身份证明文件(中文)",size = 255)
    private String appCertFilePath;

    @Column(name = "app_cert_file_en_path",header = "身份证明原文件(外文)",size = 255)
    private String appCertFileENPath;

    @Column(name = "if_share_tm",header = "是否共有商标")
    private String ifShareTm = "ck_no";

    @Column(name = "txt_bgqmyzw",header = "变更前名称(中文)")
    private String txt_bgqmyzw;

    @Column(name = "txt_bgqmyyw",header = "变更前名称(英文)")
    private String txt_bgqmyyw;

    @Column(name = "txt_bgqdzzw",header = "变更前地址(中文)")
    private String txt_bgqdzzw;

    @Column(name = "txt_bgqdzyw",header = "变更前地址(英文)")
    private String txt_bgqdzyw;

    @Column(name = "prove_language_type",header = "变更证明文件是否为中文")
    private ProveLanguageType proveLanguageType = ProveLanguageType.YES;

    @Column(name = "bgzm_file_path",header = "变更证明文件(中文)",size = 255)
    private String bgzmFilePath;

    @Column(name = "bgzm_file_en_path",header = "变更证明文件(原件)",size = 255)
    private String bgzmFileENPath;

    @Column(name = "change_trade_mark_type",header = "商标类型")
    private ChangeTradeMarkType changeTradeMarkType = ChangeTradeMarkType.GENERAL;

    @Column(name = "txt_sbsqh",header = "商标申请号/注册号")
    private String txt_sbsqh;

    @Column(name = "comment_path",header = "有关说明文件",size = 255)
    private String commentPath;

    @Column(name = "change_trade_mark_state",header = "变更商标状态")
    private ChangeTradeMarkState changeTradeMarkState = ChangeTradeMarkState.WAIT_CONFIRM;

    @Column(name = "department_id",header = "部门ID")
    private Integer departmentId;

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

    public WriteType getWriteType() {
        return writeType;
    }

    public void setWriteType(WriteType writeType) {
        this.writeType = writeType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getTxt_sqrmyzw() {
        return txt_sqrmyzw;
    }

    public void setTxt_sqrmyzw(String txt_sqrmyzw) {
        this.txt_sqrmyzw = txt_sqrmyzw;
    }

    public String getTxt_sqrmyyw() {
        return txt_sqrmyyw;
    }

    public void setTxt_sqrmyyw(String txt_sqrmyyw) {
        this.txt_sqrmyyw = txt_sqrmyyw;
    }

    public String getTxt_sqrdzzw() {
        return txt_sqrdzzw;
    }

    public void setTxt_sqrdzzw(String txt_sqrdzzw) {
        this.txt_sqrdzzw = txt_sqrdzzw;
    }

    public String getTxt_sqrdzyw() {
        return txt_sqrdzyw;
    }

    public void setTxt_sqrdzyw(String txt_sqrdzyw) {
        this.txt_sqrdzyw = txt_sqrdzyw;
    }

    public String getTxt_yzbm() {
        return txt_yzbm;
    }

    public void setTxt_yzbm(String txt_yzbm) {
        this.txt_yzbm = txt_yzbm;
    }

    public String getTxt_lxr() {
        return txt_lxr;
    }

    public void setTxt_lxr(String txt_lxr) {
        this.txt_lxr = txt_lxr;
    }

    public String getTxt_dh() {
        return txt_dh;
    }

    public void setTxt_dh(String txt_dh) {
        this.txt_dh = txt_dh;
    }

    public String getAgentFileNum() {
        return agentFileNum;
    }

    public void setAgentFileNum(String agentFileNum) {
        this.agentFileNum = agentFileNum;
    }

    public String getTxt_dlrmc() {
        return txt_dlrmc;
    }

    public void setTxt_dlrmc(String txt_dlrmc) {
        this.txt_dlrmc = txt_dlrmc;
    }

    public String getAgentBookPath() {
        return agentBookPath;
    }

    public void setAgentBookPath(String agentBookPath) {
        this.agentBookPath = agentBookPath;
    }

    public ChangeApplierType getApplierType() {
        return applierType;
    }

    public void setApplierType(ChangeApplierType applierType) {
        this.applierType = applierType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public String getCertFilePath() {
        return certFilePath;
    }

    public void setCertFilePath(String certFilePath) {
        this.certFilePath = certFilePath;
    }

    public String getCertFileENPath() {
        return certFileENPath;
    }

    public void setCertFileENPath(String certFileENPath) {
        this.certFileENPath = certFileENPath;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    public String getAppCertificateNum() {
        return appCertificateNum;
    }

    public void setAppCertificateNum(String appCertificateNum) {
        this.appCertificateNum = appCertificateNum;
    }

    public String getAppCertFilePath() {
        return appCertFilePath;
    }

    public void setAppCertFilePath(String appCertFilePath) {
        this.appCertFilePath = appCertFilePath;
    }

    public String getAppCertFileENPath() {
        return appCertFileENPath;
    }

    public void setAppCertFileENPath(String appCertFileENPath) {
        this.appCertFileENPath = appCertFileENPath;
    }

    public String getIfShareTm() {
        return ifShareTm;
    }

    public void setIfShareTm(String ifShareTm) {
        this.ifShareTm = ifShareTm;
    }

    public String getTxt_bgqmyzw() {
        return txt_bgqmyzw;
    }

    public void setTxt_bgqmyzw(String txt_bgqmyzw) {
        this.txt_bgqmyzw = txt_bgqmyzw;
    }

    public String getTxt_bgqmyyw() {
        return txt_bgqmyyw;
    }

    public void setTxt_bgqmyyw(String txt_bgqmyyw) {
        this.txt_bgqmyyw = txt_bgqmyyw;
    }

    public String getTxt_bgqdzzw() {
        return txt_bgqdzzw;
    }

    public void setTxt_bgqdzzw(String txt_bgqdzzw) {
        this.txt_bgqdzzw = txt_bgqdzzw;
    }

    public String getTxt_bgqdzyw() {
        return txt_bgqdzyw;
    }

    public void setTxt_bgqdzyw(String txt_bgqdzyw) {
        this.txt_bgqdzyw = txt_bgqdzyw;
    }

    public ProveLanguageType getProveLanguageType() {
        return proveLanguageType;
    }

    public void setProveLanguageType(ProveLanguageType proveLanguageType) {
        this.proveLanguageType = proveLanguageType;
    }

    public String getBgzmFilePath() {
        return bgzmFilePath;
    }

    public void setBgzmFilePath(String bgzmFilePath) {
        this.bgzmFilePath = bgzmFilePath;
    }

    public String getBgzmFileENPath() {
        return bgzmFileENPath;
    }

    public void setBgzmFileENPath(String bgzmFileENPath) {
        this.bgzmFileENPath = bgzmFileENPath;
    }

    public ChangeTradeMarkType getChangeTradeMarkType() {
        return changeTradeMarkType;
    }

    public void setChangeTradeMarkType(ChangeTradeMarkType changeTradeMarkType) {
        this.changeTradeMarkType = changeTradeMarkType;
    }

    public String getTxt_sbsqh() {
        return txt_sbsqh;
    }

    public void setTxt_sbsqh(String txt_sbsqh) {
        this.txt_sbsqh = txt_sbsqh;
    }

    public String getCommentPath() {
        return commentPath;
    }

    public void setCommentPath(String commentPath) {
        this.commentPath = commentPath;
    }

    public ChangeTradeMarkState getChangeTradeMarkState() {
        return changeTradeMarkState;
    }

    public void setChangeTradeMarkState(ChangeTradeMarkState changeTradeMarkState) {
        this.changeTradeMarkState = changeTradeMarkState;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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
