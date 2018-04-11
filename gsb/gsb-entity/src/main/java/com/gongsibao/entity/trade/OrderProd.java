package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.CostStatus;
import com.gongsibao.entity.trade.dic.InvoiceState;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.entity.yj.Trademark;

@Table(name = "so_order_prod")
public class OrderProd extends BaseEntity {

	private static final long serialVersionUID = -1666193798755838616L;

	@Column(header = "编 号")
	private String no = "";

	@Column(name = "order_id", header = "订单")
	private Integer orderId = 0;
	
	@Column(name = "version", header = "流程模版版本号")
	private Integer version;

	@JsonIgnore
	@Reference(foreignKey = "orderId", header = "销售订单", primaryKey = "pkid")
	private SoOrder soOrder;

	@Column(name = "product_id", header = "产品")
	private Integer productId = 0;

	@Column(name = "product_name", header = "产品名称")
	private String productName;

	@Column(name = "city_id", header = "城市")
	private Integer cityId = 0;

	@Reference(foreignKey = "cityId", header = "产品地区")
	private Dict city;

	@Column(name = "city_name", header = "地区名称(冗余)")
	private String cityName;

	@Exclusive
	private Integer quantity = 1;

	@Column(name = "company_id", header = "公司")
	private Integer companyId = 0;

	@Reference(foreignKey = "companyId", header = "公司")
	private CompanyIntention companyIntention;

	@Column(name = "yj_trademark_id", header = "商标信息Id")
	private Integer trademarkId = 0;

	@Reference(foreignKey = "trademarkId", header = "商标信息")
	private Trademark trademark;

	@Column(name = "process_status_id", header = "进度状态")
	private Integer processStatusId = 0;
	
	@Reference(foreignKey = "processStatusId", header = "进度状态")
	private WorkflowNode processStatus;

	@Column(name = "audit_status_id", header = "审核状态：type=105")
	private AuditStatusType auditStatus = AuditStatusType.wu;

	@Column(name = "price", header = "价格")
	private Integer price = 0;

	@Column(name = "price_original", header = "原价")
	private Integer priceOriginal = 0;

	@Column(name = "is_refund", header = "退款")
	private Boolean isRefund = false;

	@Column(name = "is_complaint", header = "客户抱怨")
	private Boolean isComplaint = false;

	@Column(name = "is_assign", header = "已分配")
	private Boolean isAssign = false;

	@Column(name = "processed_days", header = "已处理天数")
	private Integer processedDays = 0;

	@Column(name = "need_days", header = "待处理天数")
	private Integer needDays = 0;

	@Column(name = "timeout_days", header = "过期时间")
	private Integer timeoutDays = 0;

	@Column(name = "invoice_state", header = "发票状态：供应商开发票状态 0 待开发票 1已开发票 2已投递")
	private InvoiceState invoiceState = InvoiceState.AWAIT;

	@Column(name = "invoice_title", header = "发票抬头")
	private String invoiceTitle;

	@Column(name = "is_bbk", header = "IsBbk")
	private String isBbk = "0";

	@Column(name = "apply_no", header = "支付账号(申请号)")
	private String applyNo;

	@Column(name = "handle_name", header = "办理名称")
	private String handleName;

	@Column(name = "cost_status", header = "成本录入状态：0未录入, 1录入中, 2完成录入")
	private CostStatus costStatus = CostStatus.NOENTRY;

	@Column(name = "settle_id", header = "结算方式：打款id")
	private Integer settleIdInteger = 0;

	@Column(name = "settle_price", header = "结算价格")
	private Integer settlePrice = 0;

	@Column(name = "settle_status", header = "结算状态 0待确认 1 2已结算")
	private SettleStatus settleStatus = SettleStatus.CONFIRM_SETTLEMENT;

	@Column(name = "settle_time", header = "结算时间")
	private Date settleTime;

	@Reference(foreignKey = "productId", header = "产品")
	private Product product;

	@Column(name = "owner_id", header = "业务员Id")
	private Integer ownerId;

	@Reference(foreignKey = "ownerId", header = "业务员")
	private Employee owner;

	@Column(name = "task_id", header = "商机Id")
	private Integer taskId;

	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;

	@Column(name = "department_id", header = "部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "部门")
	private SupplierDepartment department;

	@Column(name = "customer_id", header = "客户Id")
	private Integer customerId;
	
	@Column(name = "begin_option", header = "是否显示开始操作（我的明细订单-控制显示那个操作）0-开始操作；1-变更操作组")
	private Boolean beginOption = false;
	
	@Reference(foreignKey = "customerId", header = "客户",primaryKey ="pkid" )
	private NCustomer customer;
	
	@Subs(subType = OrderProdItem.class, foreignKey = "orderProdId", header = "服务明细")
	private List<OrderProdItem> items = new ArrayList<OrderProdItem>();

	@Subs(subType = OrderProdTrace.class, foreignKey = "orderProdId", header = "跟进记录")
	private List<OrderProdTrace> traces = new ArrayList<OrderProdTrace>();

	@Subs(subType = OrderProdCost.class, foreignKey = "orderProdId", header = "订单成本")
	private List<OrderProdCost> costs = new ArrayList<OrderProdCost>();


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPriceOriginal() {
		return priceOriginal;
	}

	public void setPriceOriginal(Integer priceOriginal) {
		this.priceOriginal = priceOriginal;
	}

	public Boolean getIsComplaint() {
		return isComplaint;
	}

	public void setIsComplaint(Boolean isComplaint) {
		this.isComplaint = isComplaint;
	}

	public Boolean getIsAssign() {
		return isAssign;
	}

	public void setIsAssign(Boolean isAssign) {
		this.isAssign = isAssign;
	}

	public Integer getProcessedDays() {
		return processedDays;
	}

	public void setProcessedDays(Integer processedDays) {
		this.processedDays = processedDays;
	}

	public Integer getNeedDays() {
		return needDays;
	}

	public void setNeedDays(Integer needDays) {
		this.needDays = needDays;
	}

	public Integer getTimeoutDays() {
		return timeoutDays;
	}

	public void setTimeoutDays(Integer timeoutDays) {
		this.timeoutDays = timeoutDays;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getIsBbk() {
		return isBbk;
	}

	public void setIsBbk(String isBbk) {
		this.isBbk = isBbk;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getHandleName() {
		return handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

	public Boolean getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(Boolean isRefund) {
		this.isRefund = isRefund;
	}

	public Integer getSettleIdInteger() {
		return settleIdInteger;
	}

	public void setSettleIdInteger(Integer settleIdInteger) {
		this.settleIdInteger = settleIdInteger;
	}

	public Integer getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(Integer settlePrice) {
		this.settlePrice = settlePrice;
	}

	public Date getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}

	public List<OrderProdItem> getItems() {
		return items;
	}

	public void setItems(List<OrderProdItem> items) {
		this.items = items;
	}

	public List<OrderProdTrace> getTraces() {
		return traces;
	}

	public void setTraces(List<OrderProdTrace> traces) {
		this.traces = traces;
	}

	public SoOrder getSoOrder() {
		return soOrder;
	}

	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<OrderProdCost> getCosts() {
		return costs;
	}

	public void setCosts(List<OrderProdCost> costs) {
		this.costs = costs;
	}

	public CompanyIntention getCompanyIntention() {
		return companyIntention;
	}

	public void setCompanyIntention(CompanyIntention companyIntention) {
		this.companyIntention = companyIntention;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Boolean getBeginOption() {
		return beginOption;
	}

	public void setBeginOption(Boolean beginOption) {
		this.beginOption = beginOption;
	}

	public NCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(NCustomer customer) {
		this.customer = customer;
	}
	public Integer getTrademarkId() {
		return trademarkId;
	}

	public void setTrademarkId(Integer trademarkId) {
		this.trademarkId = trademarkId;
	}

	public Trademark getTrademark() {
		return trademark;
	}

	public void setTrademark(Trademark trademark) {
		this.trademark = trademark;
	}

	public AuditStatusType getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatusType auditStatus) {
		this.auditStatus = auditStatus;
	}

	public InvoiceState getInvoiceState() {
		return invoiceState;
	}

	public void setInvoiceState(InvoiceState invoiceState) {
		this.invoiceState = invoiceState;
	}

	public CostStatus getCostStatus() {
		return costStatus;
	}

	public void setCostStatus(CostStatus costStatus) {
		this.costStatus = costStatus;
	}

	public SettleStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getProcessStatusId() {
		return processStatusId;
	}

	public void setProcessStatusId(Integer processStatusId) {
		this.processStatusId = processStatusId;
	}

	public WorkflowNode getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(WorkflowNode processStatus) {
		this.processStatus = processStatus;
	}
	
	
}