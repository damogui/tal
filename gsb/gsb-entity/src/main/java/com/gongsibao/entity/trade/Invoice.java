package com.gongsibao.entity.trade;

import java.util.List;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.trade.dic.InvoiceOpenBallotCompanyType;
import com.gongsibao.entity.trade.dic.InvoiceType;

@Table(name = "so_invoice", header = "发票")
public class Invoice extends BaseEntity {

	private static final long serialVersionUID = -3543797519223170163L;
	@Column(name = "title", header = "抬头")
	private String title;

	@Column(name = "company_id", header = "开票公司，type=307")
	private InvoiceOpenBallotCompanyType companyId = InvoiceOpenBallotCompanyType.Htzx;

	@Column(name = "type_id", header = "开票类型，type=308")
	private InvoiceType typeId =InvoiceType.Pt;

	@Column(name = "amount", header = "发票金额")
	private Integer amount;

	@Column(name = "content", header = "发票内容")
	private String content;

	@Column(name = "audit_status_id", header = "审核状态序号，type=105")
	private AuditStatusType auditStatusId;

	@Column(name = "receiver_name", header = "接收人姓名")
	private String receiverName;

	@Column(name = "receiver_mobile_phone", header = "接收人手机")
	private String receiverMobilePhone;

	@Column(name = "receiver_address", header = "接收人地址")
	private String receiverAddress;

	@Column(name = "vat_tax_no", header = "增值税公司税号")
	private String vatTaxNo;

	@Column(name = "vat_address", header = "增值税公司注册地址")
	private String vatAddress;

	@Column(name = "vat_phone", header = "增值税公司注册电话")
	private String vatPhone;

	@Column(name = "vat_bank_name", header = "增值税公司开户行名称")
	private String vatBankName;

	@Column(name = "vat_bank_no", header = "增值税公司开户行帐号")
	private String vatBankNo;

	@Column(name = "file_id", header = "附件序号")
	private Integer fileId;

	@Column(name = "remark", header = "说明")
	private String remark;
	
	@Subs(foreignKey = "invoiceId", header = "订单和发票的中间表", subType = OrderInvoiceMap.class)
	private List<OrderInvoiceMap> orderInvoiceMaps;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public InvoiceOpenBallotCompanyType getCompanyId() {
		return companyId;
	}

	public void setCompanyId(InvoiceOpenBallotCompanyType companyId) {
		this.companyId = companyId;
	}

	public InvoiceType getTypeId() {
		return typeId;
	}

	public void setTypeId(InvoiceType typeId) {
		this.typeId = typeId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AuditStatusType getAuditStatusId() {
		return auditStatusId;
	}

	public void setAuditStatusId(AuditStatusType auditStatusId) {
		this.auditStatusId = auditStatusId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobilePhone() {
		return receiverMobilePhone;
	}

	public void setReceiverMobilePhone(String receiverMobilePhone) {
		this.receiverMobilePhone = receiverMobilePhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getVatTaxNo() {
		return vatTaxNo;
	}

	public void setVatTaxNo(String vatTaxNo) {
		this.vatTaxNo = vatTaxNo;
	}

	public String getVatAddress() {
		return vatAddress;
	}

	public void setVatAddress(String vatAddress) {
		this.vatAddress = vatAddress;
	}

	public String getVatPhone() {
		return vatPhone;
	}

	public void setVatPhone(String vatPhone) {
		this.vatPhone = vatPhone;
	}

	public String getVatBankName() {
		return vatBankName;
	}

	public void setVatBankName(String vatBankName) {
		this.vatBankName = vatBankName;
	}

	public String getVatBankNo() {
		return vatBankNo;
	}

	public void setVatBankNo(String vatBankNo) {
		this.vatBankNo = vatBankNo;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderInvoiceMap> getOrderInvoiceMaps() {
		return orderInvoiceMaps;
	}

	public void setOrderInvoiceMaps(List<OrderInvoiceMap> orderInvoiceMaps) {
		this.orderInvoiceMaps = orderInvoiceMaps;
	}
	
}