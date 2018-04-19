package com.gongsibao.entity.trade;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.InvoiceOpenBallotCompanyType;
import com.gongsibao.entity.trade.dic.InvoiceTitleType;
import com.gongsibao.entity.trade.dic.InvoiceType;
import org.netsharp.core.annotations.*;
import org.netsharp.organization.entity.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "so_invoice", header = "发票")
public class Invoice extends BaseEntity {

    private static final long serialVersionUID = -3543797519223170163L;
    @Column(name = "title", header = "抬头")
    private String title;

    @Column(name = "title_type", header = "抬头类型")
    private InvoiceTitleType titleType = InvoiceTitleType.GR;

    @Column(name = "company_id", header = "开票公司，type=307")
    private InvoiceOpenBallotCompanyType companyId = InvoiceOpenBallotCompanyType.Htzx;

    @Column(name = "type_id", header = "开票类型，type=308")
    private InvoiceType typeId = InvoiceType.Pt;

    @Column(name = "amount", header = "发票金额")
    private Integer amount;

    @Column(name = "content", header = "发票内容")
    private String content;

    @Column(name = "audit_status_id", header = "审核状态序号，type=105")
    private AuditStatusType auditStatusId = AuditStatusType.Dsh;

    @Column(name = "receiver_name", header = "接收人姓名")
    private String receiverName;

    @Column(name = "receiver_mobile_phone", header = "接收人手机")
    private String receiverMobilePhone;

    @Column(name = "receiver_address", header = "接收人地址")
    private String receiverAddress;

    @Column(name = "receiver_email", header = "接收人邮箱")
    private String receiverEmail;

    @Column(name = "vat_tax_no", header = "增值税公司税号")
    private String vatTaxNo;

    @Column(name = "vat_address", header = "增值税公司注册地址", required = false)
    private String vatAddress;

    @Column(name = "vat_phone", header = "增值税公司注册电话", required = false)
    private String vatPhone;

    @Column(name = "vat_bank_name", header = "增值税公司开户行名称", required = false)
    private String vatBankName;

    @Column(name = "vat_bank_no", header = "增值税公司开户行帐号", required = false)
    private String vatBankNo;

    @Column(name = "file_id", header = "附件序号")
    private Integer fileId;

    @Column(name = "remark", header = "说明", required = false)
    private String remark;

    @Subs(foreignKey = "invoiceId", primaryKey = "pkid", header = "订单和发票的中间表", subType = OrderInvoiceMap.class)
    private List<OrderInvoiceMap> orderInvoiceMaps;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = 0;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Column(name = "department_id", header = "部门Id")
    private Integer departmentId = 0;

    @Reference(foreignKey = "departmentId", header = "部门")
    private SupplierDepartment department;

    @Column(name = "salesman_id", header = "业务员Id")
    private Integer salesmanId;

    @Reference(foreignKey = "salesmanId")
    private Employee salesman;

    @Subs(subType = File.class, foreignKey = "formId", header = "上传图片表（一个支付可以多个凭证）")
    private List<File> files = new ArrayList<>();

    @Exclusive
    @Subs(subType = AuditLog.class, foreignKey = "formId", header = "审核记录")
    private List<AuditLog> auditLogs = new ArrayList<AuditLog>();

    //订单信息
    @Exclusive
    private SoOrder soOrder;

    //订单编号
    @Exclusive
    @Column(name = "soOrderNo")
    private String soOrderNo;

    //渠道编号
    @Exclusive
    @Column(name = "channelOrderNo")
    private String channelOrderNo;

    //产品名称
    @Exclusive
    @Column(name = "prodName")
    private String prodName;

    //新老客户签单
    @Exclusive
    @Column(name = "accountTypeName")
    private String accountTypeName;

    //订单金额
    @Exclusive
    @Column(name = "orderPayablePrice")
    private Integer orderPayablePrice;

    //订单付款金额
    @Exclusive
    @Column(name = "orderPaidPrice")
    private Integer orderPaidPrice;

    //订单创建时间
    @Exclusive
    @Column(name = "orderCreateTime")
    private Date orderCreateTime;


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

    public InvoiceTitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(InvoiceTitleType titleType) {
        this.titleType = titleType;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public SupplierDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SupplierDepartment department) {
        this.department = department;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Employee getSalesman() {
        return salesman;
    }

    public void setSalesman(Employee salesman) {
        this.salesman = salesman;
    }

    public String getSoOrderNo() {
        return soOrderNo;
    }

    public void setSoOrderNo(String soOrderNo) {
        this.soOrderNo = soOrderNo;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public Integer getOrderPayablePrice() {
        return orderPayablePrice;
    }

    public void setOrderPayablePrice(Integer orderPayablePrice) {
        this.orderPayablePrice = orderPayablePrice;
    }

    public Integer getOrderPaidPrice() {
        return orderPaidPrice;
    }

    public void setOrderPaidPrice(Integer orderPaidPrice) {
        this.orderPaidPrice = orderPaidPrice;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(List<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }
}