package com.gongsibao.entity.igirl.tm;

import com.gongsibao.entity.igirl.tm.dict.*;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.Entity;
import java.math.BigDecimal;
import java.util.List;

//@BizCode(bizType = "TradeMarkCase")
@Table(name = "ig_trade_mark_case", header = "商标方案")
public class TradeMarkCase extends Entity {
	private static final long serialVersionUID = 55983222524461776L;

	@Column(name = "code", header = "方案编号")
	private String code;

	@Column(name = "certificate_type", header = "证件名称")
	private CertificateType certificateType = null;

	// 商标说明
	@Column(name = "name", header = "方案名称")
	private String name;

	@Column(name = "mobile", header = "客户电话")
	private String mobile;

	@Column(name = "contact_name", header = "客户姓名")
	private String contactName;

	@Column(name = "yw_phone", header = "代理电话")
	private String ywPhone;

	@Column(name = "owned_marks", header = "已有商标")
	private String ownedMarks;

	@Column(name = "momo", header = "交流记录")
	private String momo;

	@Column(name = "advice", header = "客户意见")
	private String advice;

	@Column(name = "tmc_state", header = "方案状态")
	private TMCState tmcState = TMCState.WAIT_CONFIRM;

	@Column(name = "applier_type", header = "申请人类型")
	private ApplierType applierType = ApplierType.PUBLIC;

	@Column(name = "applier", header = "申请人")
	private String applier;

	@Column(name = "company_name", header = "公司名称")
	private String companyName;

	@Column(name = "credit_code", header = "统一社会信用代码")
	private String creditCode;

	@Column(name = "fax", header = "传真（含地区号）")
	private String fax;

	@Column(name = "applier_address",size = 256, header = "申请人地址")
	private String applierAddress;

	@Column(name = "identity_code", header = "申请人身份证")
	private String identityCode;

	@Column(name = "mail_code", header = "邮编")
	private String mailCode;

	@Column(name = "write_type", header = "书式类型")
	private WriteType writeType = WriteType.DALU;

	@Column(name = "token", header = "分享方案表识")
	private String token;

	@Column(name = "token_img_url", size = 256, header = "二纬码")
	private String tokenImgUrl;

	@Column(name = "case_amount", header = "金额")
	private BigDecimal caseAmount = BigDecimal.ZERO;

	@Column(name = "order_code", header = "订单号")
	private String orderCode;

	// 显示大类的选择

	// 显示大类的选择
	@Column(name = "trade_options", header = "商标选项")
	private String tradeOptions;

	@Column(name = "proxy_company_name", header = "代理商名称")
	private String proxyCompanyName;

	@Column(name = "case_proxy_contact_person", header = "代理联系人")
	private String caseProxyContactPerson;

	@Column(name = "bank_account", header = "开户行")
	private String bankAccount;

	@Column(name = "account_no", header = "账号")
	private String accountNo;

	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId = -1;
	@JsonIgnore
	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;

	@Subs(foreignKey = "tradeMarkCaseId", header = "商标明细", subType = TradeMark.class)
	private List<TradeMark> tradeMarks;

	@Subs(foreignKey = "tradeMarkCaseId", header = "上传附件", subType = UploadAttachment.class)
	private List<UploadAttachment> uploadAttachments;

	@Subs(foreignKey = "tradeMarkCaseId", header = "下载附件", subType = DownloadAttachment.class)
	private List<DownloadAttachment> downLoadAttaments;

	// 期望时间
	@Column(name = "urgency", header = "紧急程度")
	private int urgency = 72;

	@Column(name = "department_id",header = "所属部门id")
	private Integer departmentId;

	@Column(name="owner_id" ,header = "当前业务员id")
	private Integer ownerId;

	@Column(name="owner_name",header = "当前业务员姓名")
	private String ownerName;

	@Column(name = "product_id", header = "商标产品id")
	private Integer productId;

	@Reference(foreignKey = "productId", header = "商标产品")
	private Product product;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMomo() {
		return momo;
	}

	public void setMomo(String momo) {
		this.momo = momo;
	}

	public ApplierType getApplierType() {
		return applierType;
	}

	public void setApplierType(ApplierType applierType) {
		this.applierType = applierType;
	}

	public String getApplier() {
		return applier;
	}

	public void setApplier(String applier) {
		this.applier = applier;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getApplierAddress() {
		return applierAddress;
	}

	public void setApplierAddress(String applierAddress) {
		this.applierAddress = applierAddress;
	}

	public String getProxyCompanyName() {
		return proxyCompanyName;
	}

	public void setProxyCompanyName(String proxyCompanyName) {
		this.proxyCompanyName = proxyCompanyName;
	}

	public String getOwnedMarks() {
		return ownedMarks;
	}

	public void setOwnedMarks(String ownedMarks) {
		this.ownedMarks = ownedMarks;
	}

	public int getUrgency() {
		return urgency;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

	public List<UploadAttachment> getUploadAttachments() {
		return uploadAttachments;
	}

	public void setUploadAttachments(List<UploadAttachment> uploadAttachments) {
		this.uploadAttachments = uploadAttachments;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public WriteType getWriteType() {
		return writeType;
	}

	public void setWriteType(WriteType writeType) {
		this.writeType = writeType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenImgUrl() {
		return tokenImgUrl;
	}

	public void setTokenImgUrl(String tokenImgUrl) {
		this.tokenImgUrl = tokenImgUrl;
	}

	public BigDecimal getCaseAmount() {
		return caseAmount;
	}

	public void setCaseAmount(BigDecimal caseAmount) {
		this.caseAmount = caseAmount;
	}

	public String getTradeOptions() {
		return tradeOptions;
	}

	public void setTradeOptions(String tradeOptions) {
		this.tradeOptions = tradeOptions;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public List<TradeMark> getTradeMarks() {
		return tradeMarks;
	}

	public void setTradeMarks(List<TradeMark> tradeMarks) {
		this.tradeMarks = tradeMarks;
	}

	public List<DownloadAttachment> getDownLoadAttaments() {
		return downLoadAttaments;
	}

	public void setDownLoadAttaments(List<DownloadAttachment> downLoadAttaments) {
		this.downLoadAttaments = downLoadAttaments;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public TMCState getTmcState() {
		return tmcState;
	}

	public void setTmcState(TMCState tmcState) {
		this.tmcState = tmcState;
	}

	public String getYwPhone() {
		return ywPhone;
	}

	public void setYwPhone(String ywPhone) {
		this.ywPhone = ywPhone;
	}

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

	public String getCaseProxyContactPerson() {
		return caseProxyContactPerson;
	}

	public void setCaseProxyContactPerson(String caseProxyContactPerson) {
		this.caseProxyContactPerson = caseProxyContactPerson;
	}

	public CertificateType getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(CertificateType certificateType) {
		this.certificateType = certificateType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}