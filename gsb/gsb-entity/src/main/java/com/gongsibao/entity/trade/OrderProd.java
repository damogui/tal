package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.entity.product.Product;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod")
public class OrderProd extends BaseEntity {
	
	private static final long serialVersionUID = -1666193798755838616L;
	
	@Column(header="编号")
    private String no;
	
    @Column(name="order_id",header="订单")
    private Integer orderId;
    
    @Column(name="product_id",header="产品")
    private Integer productId;
    
    @Column(name="product_name",header="产品名称")
    private String productName;
    
    @Column(name="city_id",header="城市")
    private Integer cityId;
    
    @Column(name="company_id",header="公司")
    private Integer companyId;
    
    @Column(name="process_status_id",header="进度")
    private Integer processStatusId;
    
    @Column(name="audit_status_id",header="审核状态")
    private Integer auditStatusId;
    
    @Column(name="price",header="价格")
    private Integer price;
    
    @Column(name="price_original",header="原价")
    private Integer priceOriginal;
    
    @Column(name="is_refund",header="退款")
    private Integer isRefund;
    
    @Column(name="is_complaint",header="客户抱怨")
    private Integer isComplaint;
    
    @Column(name="is_assign",header="已分配")
    private Integer isAssign;
    
    @Column(name="processed_days",header="已处理天数")
    private Integer processedDays;
    
    @Column(name="need_days",header="待处理天数")
    private Integer needDays;
    
    @Column(name="timeout_days",header="过期时间")
    private Integer timeoutDays;
    
    @Column(name="invoice_state",header="发票状态")
    private Integer invoiceState;
    
    @Column(name="invoice_title",header="发票抬头")
    private String invoiceTitle;
    
    @Column(name="is_bbk",header="IsBbk")
    private String isBbk="0";
    
    @Column(name="apply_no",header="支付账号")
    private String applyNo;
    
    @Column(name="handle_name",header="处理人")
    private String handleName;
    
    @Column(name="cost_status",header="成本状态")
    private Integer costStatus;
    
    @Column(name="settle_id",header="结算方式")
    private Integer settleId;
    
    @Column(name="settle_price",header="结算价格")
    private Integer settlePrice;
    
    @Column(name="settle_status",header="结算状态")
    private Integer settleStatus;
    
    @Column(name="settle_time",header="结算时间")
    private Date settleTime;
    
    @Subs(subType=OrderProdItem.class,foreignKey="orderProdId",header="服务明细")
    private List<OrderProdItem> items = new ArrayList<OrderProdItem>();

    @Subs(subType=OrderProdTrace.class,foreignKey="orderProdId",header="跟进记录")
    private List<OrderProdTrace> traces = new ArrayList<OrderProdTrace>();

    @Reference(foreignKey = "orderId",header="销售订单")
    private SoOrder soOrder;

    @Reference(foreignKey = "productId",header="产品")
    private Product product;
    
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
    public Integer getProcessStatusId() {
        return processStatusId;
    }
    public void setProcessStatusId(Integer processStatusId) {
        this.processStatusId = processStatusId;
    }
    public Integer getAuditStatusId() {
        return auditStatusId;
    }
    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
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
    public Integer getIsRefund() {
        return isRefund;
    }
    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }
    public Integer getIsComplaint() {
        return isComplaint;
    }
    public void setIsComplaint(Integer isComplaint) {
        this.isComplaint = isComplaint;
    }
    public Integer getIsAssign() {
        return isAssign;
    }
    public void setIsAssign(Integer isAssign) {
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
    public Integer getInvoiceState() {
        return invoiceState;
    }
    public void setInvoiceState(Integer invoiceState) {
        this.invoiceState = invoiceState;
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
    public Integer getCostStatus() {
        return costStatus;
    }
    public void setCostStatus(Integer costStatus) {
        this.costStatus = costStatus;
    }
    public Integer getSettleId() {
        return settleId;
    }
    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }
    public Integer getSettlePrice() {
        return settlePrice;
    }
    public void setSettlePrice(Integer settlePrice) {
        this.settlePrice = settlePrice;
    }
    public Integer getSettleStatus() {
        return settleStatus;
    }
    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
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
}