package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.product.ProductPackage;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.OrderAccountType;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.entity.trade.dic.OrderPayStatusType;
import com.gongsibao.entity.trade.dic.OrderPlatformSourceType;
import com.gongsibao.entity.trade.dic.OrderProcessStatusType;
import com.gongsibao.entity.trade.dic.OrderRefundStatusType;
import com.gongsibao.entity.trade.dic.OrderSourceType;
import com.gongsibao.entity.trade.dic.OrderType;
import com.gongsibao.entity.uc.Account;

@Table(name = "so_order", header = "销售订单")
public class SoOrder extends BaseEntity {

    private static final long serialVersionUID = 8766647536940983034L;

    @Column(name = "no", header = "编码")
    private String no = "1";

    @Column(name = "type", header = "订单类型")
    private OrderType type = OrderType.Dd;

	/*
     * @Reference(foreignKey="type",header="订单类型") private Dict typeDict;
	 */

    @Column(name = "task_id", header = "任务Id")
    private Integer taskId;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Column(name = "department_id", header = "部门Id")
    private Integer departmentId;

    @Reference(foreignKey = "departmentId", header = "部门")
    private SupplierDepartment department;

    @Column(name = "account_id", header = "客户")
    private Integer accountId;

    @Reference(foreignKey = "accountId", header = "客户")
    private Account account;

    @Column(name = "account_name", header = "账户名称")
    private String accountName = "";

    @Column(name = "account_mobile", header = "手机号")
    private String accountMobile = "";

    // 3011 待付款
    // 3012 已付部分款（根据“是否分期”判断处理流程）
    // 3013 已付款
    @Column(name = "pay_status_id", header = "支付状态：type=301")
    private OrderPayStatusType payStatus = OrderPayStatusType.Dhk;

    @Column(name = "stage_num", header = "分期次数（待讨论）")
    private Integer stageNum;

    @Column(name = "pay_time", header = "支付时间")
    private Date payTime;

    @Column(name = "channel_order_no", header = "渠道订单号")
    private String channelOrderNo;

    // 3021 待办理
    // 3022 正在办理
    // 3023 已取消
    // 3024 已完成
    @Column(name = "process_status_id", header = "执行进度：type=302")
    private OrderProcessStatusType processStatus = OrderProcessStatusType.Dbl;


    // 3031 待审核
    // 3032 退款中
    // 3033 退款完成
    // 3034 驳回退款
    @Column(name = "refund_status_id", header = "退款状态：type=303")
    private OrderRefundStatusType refundStatus = OrderRefundStatusType.wu;

    @Column(name = "total_price", header = "总金额")
    private Integer totalPrice = 0;

    @Column(name = "payable_price", header = "应付金额")
    private Integer payablePrice = 0;

    @Column(name = "paid_price", header = "已支付金额")
    private Integer paidPrice = 0;

    @Column(name = "performance_price", header = "已划分金额(业绩已经划分)")
    private   Integer performancePrice = 0;

    @Subs(subType = NDepReceivable.class, foreignKey = "orderId", header = "业绩划分表")
    private List<NDepReceivable> depReceivable  ;

    @Column(name = "discount_price", header = "优惠金额")
    private Integer discountPrice = 0;

    @Column(name = "source_type_id", header = "来源类型")
    private OrderSourceType sourceType = OrderSourceType.wu;

    @Column(name = "is_installment", header = "多次支付")
    private Boolean isInstallment = false;

    @Column(name = "installment_mode", header = "多次支付方式")
    private String installmentMode = "";

    // 1051 待审核
    // 1052 审核中
    // 1053 驳回审核
    // 1054 审核通过
    @Column(name = "installment_audit_status_id", header = "多次支付状态：type=105")
    private AuditStatusType installmentAuditStatusId = AuditStatusType.Dsh;

    /*@Reference(foreignKey = "installmentAuditStatusId", header = "多次支付状态")
    private Dict installmentAuditStatus;*/

    @Column(name = "is_change_price", header = "改过价")
    private Boolean isChangePrice = false;

    @Column(name = "is_carry_over", header = "是否是结转订单，默认否")
    private Boolean isCarryOver = false;

    @Column(name = "carry_over_order_id", header = "结转订单id")
    private Integer carryOverOrderId;

    @Column(name = "customer_id", header = "客户Id")
    private Integer customerId;

    @Reference(foreignKey = "customerId", header = "客户")
    private NCustomer customer;

    @Column(name = "customer_name", header = "客户名称")
    private String customerName = "";


    // 是否生成u8凭证手动处理（异常）（0：否、1：是(跨月异常)） 2:（e支付（财务二维码））、（刷卡）付款方式标记异常
    // 3:由于借贷方金额都为零，无法生成凭证（【确认收入凭证】，金额太小造成，如：0.01，0.1）
    // 4:由于借贷方金额都为零，无法生成凭证（【退款凭证】，金额太小造成，如：0.01，0.1）
    @Column(name = "is_manual_voucher", header = "是否生成u8凭证手动处理（异常）")
    private OrderManualVoucherStatus isManualVoucher = OrderManualVoucherStatus.NotStarted;

    @Column(name = "manual_voucher_status", header = "凭证手动处理状态（0:未完成 1:已完成）")
    private OrderManualVoucherStatus manualVoucherStatus = OrderManualVoucherStatus.NotStarted;

    // 1051 待审核
    // 1052 审核中
    // 1053 驳回审核
    // 1054 审核通过
    @Column(name = "change_price_audit_status_id", header = "改价审核状态：type=105")
    private AuditStatusType changePriceAuditStatus = AuditStatusType.wu;

    @Column(name = "is_invoice", header = "开票")
    private Boolean isInvoice = false;

    @Column(name = "description", header = "description")
    private String description = "";

    @Column(name = "is_package", header = "套餐")
    private Boolean isPackage = false;

    @Column(name = "package_id", header = "套餐")
    private Integer packageId = 0;

    @Reference(foreignKey = "packageId", header = "套餐")
    private ProductPackage packageProduct;

    @Column(name = "is_bbk", header = "IsBbk")
    private String isBbk = "0";

    @Column(name = "add_user_id", header = "创建人")
    private Integer addUserId = 0;

    @Reference(foreignKey = "addUserId", header = "创建人")
    private Employee addUser;

    @Column(name = "owner_id", header = "业务员Id")
    private Integer ownerId;

    @Reference(foreignKey = "ownerId", header = "业务员")
    private Employee owner;

    @Column(name = "prod_name", header = "产品名称")
    private String prodName = "";

    @Column(name = "is_delete", header = "已删除")
    private Boolean isDelete = false;

    @Column(name = "company_id", header = "公司")
    private Integer companyId = 0;

    @Reference(foreignKey = "companyId", header = "公司")
    private CompanyIntention companyIntention;

    @Column(name = "remark", header = "备注")
    private String remark = "";

    /*
     * 32101 公司宝 32102 腾讯众创空间 32103 阿里云 32104 星河互联 32105 供应商 32106 微信商城 32108 钉钉
     */
    @Column(name = "platform_source", header = "平台来源：type=321,自营默认【公司宝】，服务商默认【供应商】")
    private OrderPlatformSourceType platformSource = OrderPlatformSourceType.Gsb;

    @Column(name = "deliver_id", header = "邮寄人")
    private Integer deliverId = 0;

    @Reference(foreignKey = "deliverId", header = "邮寄人")
    private Employee deliver;

    @Column(name = "deliver_addr", header = "邮寄地址")
    private String deliverAddr = "";

    @Column(name = "account_type", header = "客户类型 0 默认 1新客户签单 2老客户签单")
    private OrderAccountType accountType = OrderAccountType.wu;

    @Column(name = "is_expire_sms", header = "过期短信提醒")
    private Boolean isExpireSms = false;

    @Column(name = "coupon_code", header = "优惠劵编码")
    private String couponCode = "";

    @Column(name = "trademark_case_id", header = "商标方案Id")
    private Integer tradeMarkCaseId = 0;

    @Reference(foreignKey = "tradeMarkCaseId", header = "商标方案")
    private TradeMarkCase tradeMarkCase;

    @Subs(subType = OrderProd.class, foreignKey = "orderId", header = "产品明细")
    private List<OrderProd> products = new ArrayList<OrderProd>();

    @Subs(subType = OrderPayMap.class, foreignKey = "orderId", header = "支付明细")
    private List<OrderPayMap> pays = new ArrayList<OrderPayMap>();

    @Subs(subType = Refund.class, foreignKey = "orderId", header = "退款明细")
    private List<Refund> redunds = new ArrayList<Refund>();

    @Subs(subType = OrderDiscount.class, foreignKey = "orderId", header = "优惠明细")
    private List<OrderDiscount> discounts = new ArrayList<OrderDiscount>();

    @Subs(subType = OrderInvoiceMap.class, foreignKey = "orderId", header = "发票信息")
    private List<OrderInvoiceMap> invoices = new ArrayList<OrderInvoiceMap>();

    @Subs(subType = AuditLog.class, foreignKey = "formId", header = "改价审核日志")
    private List<AuditLog> auditLogs = new ArrayList<AuditLog>();

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public NCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(NCustomer customer) {
        this.customer = customer;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPayablePrice() {
        return payablePrice;
    }

    public void setPayablePrice(Integer payablePrice) {
        this.payablePrice = payablePrice;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }


    public Boolean getIsInstallment() {
        return isInstallment;
    }

    public void setIsInstallment(Boolean isInstallment) {
        this.isInstallment = isInstallment;
    }

    public String getInstallmentMode() {
        return installmentMode;
    }

    public void setInstallmentMode(String installmentMode) {
        this.installmentMode = installmentMode;
    }

    public AuditStatusType getInstallmentAuditStatusId() {
        return installmentAuditStatusId;
    }

    public void setInstallmentAuditStatusId(AuditStatusType installmentAuditStatusId) {
        this.installmentAuditStatusId = installmentAuditStatusId;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(Boolean isPackage) {
        this.isPackage = isPackage;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getIsBbk() {
        return isBbk;
    }

    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliverAddr() {
        return deliverAddr;
    }

    public void setDeliverAddr(String deliverAddr) {
        this.deliverAddr = deliverAddr;
    }

    public OrderAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(OrderAccountType accountType) {
        this.accountType = accountType;
    }

    public Boolean getIsExpireSms() {
        return isExpireSms;
    }

    public void setIsExpireSms(Boolean isExpireSms) {
        this.isExpireSms = isExpireSms;
    }

    public List<OrderPayMap> getPays() {
        return pays;
    }

    public void setPays(List<OrderPayMap> pays) {
        this.pays = pays;
    }

    public List<Refund> getRedunds() {
        return redunds;
    }

    public void setRedunds(List<Refund> redunds) {
        this.redunds = redunds;
    }

    public List<OrderProd> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProd> products) {
        this.products = products;
    }


    public Employee getAddUser() {
        return addUser;
    }

    public void setAddUser(Employee addUser) {
        this.addUser = addUser;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public Employee getDeliver() {
        return deliver;
    }

    public void setDeliver(Employee deliver) {
        this.deliver = deliver;
    }

    public ProductPackage getPackageProduct() {
        return packageProduct;
    }

    public void setPackageProduct(ProductPackage packageProduct) {
        this.packageProduct = packageProduct;
    }

    public CompanyIntention getCompanyIntention() {
        return companyIntention;
    }

    public void setCompanyIntention(CompanyIntention companyIntention) {
        this.companyIntention = companyIntention;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<OrderDiscount> discounts) {
        this.discounts = discounts;
    }

    public Integer getCarryOverOrderId() {
        return carryOverOrderId;
    }

    public void setCarryOverOrderId(Integer carryOverOrderId) {
        this.carryOverOrderId = carryOverOrderId;
    }

    public OrderManualVoucherStatus getIsManualVoucher() {
        return isManualVoucher;
    }

    public void setIsManualVoucher(OrderManualVoucherStatus isManualVoucher) {
        this.isManualVoucher = isManualVoucher;
    }

    public OrderManualVoucherStatus getManualVoucherStatus() {
        return manualVoucherStatus;
    }

    public void setManualVoucherStatus(OrderManualVoucherStatus manualVoucherStatus) {
        this.manualVoucherStatus = manualVoucherStatus;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Boolean getIsChangePrice() {
        return isChangePrice;
    }

    public void setIsChangePrice(Boolean isChangePrice) {
        this.isChangePrice = isChangePrice;
    }

    public Boolean getIsCarryOver() {
        return isCarryOver;
    }

    public void setIsCarryOver(Boolean isCarryOver) {
        this.isCarryOver = isCarryOver;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getPerformancePrice() {
        return performancePrice;
    }

    public void setPerformancePrice(Integer performancePrice) {
        this.performancePrice = performancePrice;
    }

    public Integer getTradeMarkCaseId() {
        return tradeMarkCaseId;
    }

    public void setTradeMarkCaseId(Integer tradeMarkCaseId) {
        this.tradeMarkCaseId = tradeMarkCaseId;
    }

    public TradeMarkCase getTradeMarkCase() {
        return tradeMarkCase;
    }

    public void setTradeMarkCase(TradeMarkCase tradeMarkCase) {
        this.tradeMarkCase = tradeMarkCase;
    }

    public OrderPayStatusType getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(OrderPayStatusType payStatus) {
        this.payStatus = payStatus;
    }

    public OrderProcessStatusType getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(OrderProcessStatusType processStatus) {
        this.processStatus = processStatus;
    }

    public OrderRefundStatusType getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(OrderRefundStatusType refundStatus) {
        this.refundStatus = refundStatus;
    }

    public OrderSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(OrderSourceType sourceType) {
        this.sourceType = sourceType;
    }

    
    public AuditStatusType getChangePriceAuditStatus() {
		return changePriceAuditStatus;
	}

	public void setChangePriceAuditStatus(AuditStatusType changePriceAuditStatus) {
		this.changePriceAuditStatus = changePriceAuditStatus;
	}

	public OrderPlatformSourceType getPlatformSource() {
        return platformSource;
    }

    public void setPlatformSource(OrderPlatformSourceType platformSource) {
        this.platformSource = platformSource;
    }

    public List<OrderInvoiceMap> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<OrderInvoiceMap> invoices) {
        this.invoices = invoices;
    }

    public List<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(List<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<NDepReceivable> getDepReceivable() {
        return depReceivable;
    }

    public void setDepReceivable(List<NDepReceivable> depReceivable) {
        this.depReceivable = depReceivable;
    }

    public Integer getStageNum() {
        return stageNum;
    }

    public void setStageNum(Integer stageNum) {
        this.stageNum = stageNum;
    }
}