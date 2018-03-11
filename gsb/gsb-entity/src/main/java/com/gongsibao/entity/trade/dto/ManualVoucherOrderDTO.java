package com.gongsibao.entity.trade.dto;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.trade.dic.OrderIsManualVoucherType;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;

@Table(name = "manual_voucher_order_dto", isView = true)
public class ManualVoucherOrderDTO extends Persistable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7990009311195701582L;

	/* id必须存在否者运行失败 */
	@Id
	@Auto
	private Integer id;

	//业务员
	private String operator;
	
	//客户名称
	private String custName;
	
	//订单编号
	private String orderNo;
	
	//订单应付金额
	private double payablePrice;
	
	//订单已付金额
	private double paidPrice;
	
	//手动凭证状态
	private OrderManualVoucherStatus manualVoucherStatus = OrderManualVoucherStatus.NotStarted;
	
	//是否生成u8凭证手动处理（异常）（0：否、1：是(跨月异常)） 2:（e支付（财务二维码））、（刷卡）付款方式标记异常 3:由于借贷方金额都为零，无法生成凭证（【确认收入凭证】，金额太小造成，如：0.01，0.1） 4:由于借贷方金额都为零，无法生成凭证（【退款凭证】，金额太小造成，如：0.01，0.1）、5、结转订单
	private OrderIsManualVoucherType isManualVoucher =OrderIsManualVoucherType.Hkky ;
	
	//首款回款日期
	private Date returnTime;
	
	//订单创建日期
	private Date addTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public OrderManualVoucherStatus getManualVoucherStatus() {
		return manualVoucherStatus;
	}

	public void setManualVoucherStatus(OrderManualVoucherStatus manualVoucherStatus) {
		this.manualVoucherStatus = manualVoucherStatus;
	}

	public OrderIsManualVoucherType getIsManualVoucher() {
		return isManualVoucher;
	}

	public void setIsManualVoucher(OrderIsManualVoucherType isManualVoucher) {
		this.isManualVoucher = isManualVoucher;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
}
