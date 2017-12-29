package com.gongsibao.entity.trade.dto;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.trade.dic.OrderPlatformSourceType;
import com.gongsibao.entity.trade.dic.OrderRefundStatusName;
import com.gongsibao.entity.trade.dic.OrderStatusName;

@Table(name = "", isView = true)
public class SoOrderDTO extends Persistable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3574017927414380114L;
	
	
	/* id必须存在否者运行失败（申请编号） */
	@Id
	@Auto
	private Integer id;
	//订单编号
	private String orderNo;	
	//渠道订单编号
	private String channelOrderNo;
	//回款日期
	private Date payTime;
	//产品名称
	private String productName;
	//订单状态
	private OrderStatusName orderStatus = OrderStatusName.Ddfk;
	//关联企业
	private String companyName ;
	//退单状态
	private OrderRefundStatusName RefundStatus = OrderRefundStatusName.Dsh;
	//原价金额
	private double totalPrice ;
	//应付金额
	private double payablePrice;
	//已付金额
	private double paidPrice;
	//分期付款
	private boolean isInstallment;
	//业务员
	private String operator;
	//下单人名称
	private String accountName;
	//客户名称
	private String customerName;
	//下单人手机号
	private String accountMobile;
	//下单人id
	private Integer accountId;
	//订单来源
	private OrderPlatformSourceType platformSource = OrderPlatformSourceType.Gsb;
	//下单时间
	private Date addTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getChannelOrderNo() {
		return channelOrderNo;
	}
	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public OrderStatusName getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatusName orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public OrderRefundStatusName getRefundStatus() {
		return RefundStatus;
	}
	public void setRefundStatus(OrderRefundStatusName refundStatus) {
		RefundStatus = refundStatus;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getPayablePrice() {
		return payablePrice;
	}
	public void setPayablePrice(double payablePrice) {
		this.payablePrice = payablePrice;
	}
	public double getPaidPrice() {
		return paidPrice;
	}
	public void setPaidPrice(double paidPrice) {
		this.paidPrice = paidPrice;
	}

	public boolean isInstallment() {
		return isInstallment;
	}
	public void setInstallment(boolean isInstallment) {
		this.isInstallment = isInstallment;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public OrderPlatformSourceType getPlatformSource() {
		return platformSource;
	}
	public void setPlatformSource(OrderPlatformSourceType platformSource) {
		this.platformSource = platformSource;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAccountMobile() {
		return accountMobile;
	}
	public void setAccountMobile(String accountMobile) {
		this.accountMobile = accountMobile;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}	

	
	
}
