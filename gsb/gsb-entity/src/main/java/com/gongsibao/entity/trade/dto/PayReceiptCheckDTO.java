package com.gongsibao.entity.trade.dto;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.trade.dic.PayForOrderCountType;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;

@Table(name = "pay_receipt_check_dto", isView = true)
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
	
	// orderId字符串
	private String orderIdStr;

	// 订单编号
	private String orderNo;

	// 订单应付金额
	private double payablePrice;
	
	// 订单应付金额字符串
	private String payablePriceStr;

	// 订单已付金额
	private double paidPrice;

	// 订单已付金额字符串
	private String paidPriceStr;

	// 支付回单号
	private String receiptNo;
	
	//u8凭证id
	private String u8VoucherId;

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
	
	// 订单创建日期字符串
	private String addTimeStr;

	// 回款日期
	private Date returnTime;
	// 账套id
	private Integer bookId;

	// 支付订单数量（0:一笔单单 1:一笔多单）
	private PayForOrderCountType payForOrderCount = PayForOrderCountType.Ybdd;

	@Reference(foreignKey = "bookId")
	private SetOfBooks book;

	private Integer bankId;

	@Reference(foreignKey = "bankId")
	private U8Bank bank;

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

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public SetOfBooks getBook() {
		return book;
	}

	public void setBook(SetOfBooks book) {
		this.book = book;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public U8Bank getBank() {
		return bank;
	}

	public void setBank(U8Bank bank) {
		this.bank = bank;
	}

	public PayForOrderCountType getPayForOrderCount() {
		return payForOrderCount;
	}

	public void setPayForOrderCount(PayForOrderCountType payForOrderCount) {
		this.payForOrderCount = payForOrderCount;
	}

	public String getOrderIdStr() {
		return orderIdStr;
	}

	public void setOrderIdStr(String orderIdStr) {
		this.orderIdStr = orderIdStr;
	}

	public String getPayablePriceStr() {
		return payablePriceStr;
	}

	public void setPayablePriceStr(String payablePriceStr) {
		this.payablePriceStr = payablePriceStr;
	}

	public String getPaidPriceStr() {
		return paidPriceStr;
	}

	public void setPaidPriceStr(String paidPriceStr) {
		this.paidPriceStr = paidPriceStr;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getU8VoucherId() {
		return u8VoucherId;
	}

	public void setU8VoucherId(String u8VoucherId) {
		this.u8VoucherId = u8VoucherId;
	}
	
	

}
