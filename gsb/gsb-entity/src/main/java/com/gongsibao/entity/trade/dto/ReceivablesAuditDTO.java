package com.gongsibao.entity.trade.dto;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name = "receivables_audit_dto", isView = true)
public class ReceivablesAuditDTO extends Persistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4951549520725519399L;

	/* id必须存在否者运行失败（申请编号） */
	@Id
	@Auto
	private Integer id;
	
	//支付id
	private Integer payId;
	
	//关联订单号
	private String orderNos;
	
	//本次申请金额
	private double amount;
	
	//付款账套
	private String bookName;
	
	//付款方式
	private String bankName;
	
	//付款账户名称
	private String offlinePayerName;
	
	//付款账号
	private String offlineBankNo;
	
	//申请人
	private String applyUserName;
	
	//审核状态id
	private Integer auditStatusId;
	
	//审核状态
	private String auditStatusName;
	
	//申请时间
	private Date addTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
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

	public String getOfflinePayerName() {
		return offlinePayerName;
	}

	public void setOfflinePayerName(String offlinePayerName) {
		this.offlinePayerName = offlinePayerName;
	}

	public String getOfflineBankNo() {
		return offlineBankNo;
	}

	public void setOfflineBankNo(String offlineBankNo) {
		this.offlineBankNo = offlineBankNo;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public Integer getAuditStatusId() {
		return auditStatusId;
	}

	public void setAuditStatusId(Integer auditStatusId) {
		this.auditStatusId = auditStatusId;
	}

	public String getAuditStatusName() {
		return auditStatusName;
	}

	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
	
	
}