package com.gongsibao.entity.trade.dto;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.trade.dic.PayReceiptStatus;

@Table(name = "pay_receipt_check_view", isView = true)
public class PayReceiptCheckDTO extends Persistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 744623124771034969L;

	/* id必须存在否者运行失败 */
	@Id
	@Auto
	private Integer id;

	// 订单id
	private Integer orderId;

	// 订单编号
	private String orderNo;

	// 订单应付金额
	private double payablePrice;

	// 订单已付金额
	private double paidPrice;

	// 支付回单号
	private String receiptNo;

	// 支付回单状态
	private PayReceiptStatus receiptStatus = PayReceiptStatus.NotStarted;

	// 支付金额
	private double amount;

	// 订单id
	private String bookName;

	// 支付方式
	private String bankName;

	// 订单创建日期
	private Date addTime;

	// 回款日期
	private Date returnTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public PayReceiptStatus getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(PayReceiptStatus receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

}
