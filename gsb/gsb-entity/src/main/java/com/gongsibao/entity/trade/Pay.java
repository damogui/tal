package com.gongsibao.entity.trade;


import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.trade.dic.PayForOrderCountType;
import com.gongsibao.entity.trade.dic.PayOfflineInstallmentType;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.entity.uc.User;

@Table(name="so_pay")
public class Pay extends BaseEntity {
	

	private static final long serialVersionUID = 2379304079106456618L;
	
	@Column(header="编号")
    private String no;
	
    @Column(header="金额")
    private Integer amount;
    
    @Column(name="pay_way_type_id",header="支付类型")
    private Integer payWayTypeId;
    
	@Reference(foreignKey="payWayTypeId",header="支付类型,type=310")
	private Dict payWayType;
    
    @Column(name="success_status_id",header="成功状态")
    private Integer successStatusId;
    
	@Reference(foreignKey="successStatusId",header="成功状态,type=312")
	private Dict successStatus;
    
    @Column(name="confirm_time",header="确认时间")
    private Date confirmTime;
    
    @Column(name="offline_way_type_id",header="线下类型")
    private Integer offlineWayTypeId;
    
	@Reference(foreignKey="offlineWayTypeId",header="线下类型")
	private Dict offlineWayType;
    
    @Column(name="offline_installment_type",header="线下结算类型,线下分期类型序号，type= 全款为0，首款为1，尾款为-1，二期为2，三期为3，以此类推")
    private PayOfflineInstallmentType offlineInstallmentTypeId=PayOfflineInstallmentType.sk;
    
	@Reference(foreignKey="offlineInstallmentTypeId",header="线下结算类型")
	private Dict offlineInstallmentType;
    
    @Column(name="offline_payer_name",header="线下支付名称")
    private String offlinePayerName;
    
    @Column(name="offline_bank_no",header="线下银行卡号")
    private String offlineBankNo;
    
    @Column(name="offline_remark",header="线下备注")
    private String offlineRemark;
    
    @Column(name="offline_audit_status_id",header="审核状态")
    private Integer offlineAuditStatusId;
    
	@Reference(foreignKey="offlineAuditStatusId",header="审核状态,type=105")
	private Dict offlineAuditStatus;
    
    @Column(name="offline_add_user_id",header="线下创建人")
    private Integer offlineAddUserId;
    
	@Reference(foreignKey="offlineAddUserId",header="线下创建人")
	private User offlineAddUser;
    
    @Column(name="online_bank_code_id",header="在线银行卡")
    private String onlineBankCodeId;
    
    @Column(name="online_trade_no",header="在线交易号")
    private String onlineTradeNo;
    
    @Column(name="receipt_no",header="回单编号")
    private String receiptNo;
    
    @Column(name="receipt_status",header="回单处理状态（0：未完成 1已完成：）")
    private PayReceiptStatus receiptStatus=PayReceiptStatus.NotStarted;
    
    @Subs(foreignKey="accountId",header="下单记录",subType=OrderPayMap.class)
    private List<OrderPayMap> orderPayMaps;
    
    @Column(name="pay_for_order_count",header="支付订单数量（0:一笔单单 1:一笔多单）")
    private PayForOrderCountType payForOrderCount = PayForOrderCountType.Ybdd;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPayWayTypeId() {
		return payWayTypeId;
	}

	public void setPayWayTypeId(Integer payWayTypeId) {
		this.payWayTypeId = payWayTypeId;
	}

	public Dict getPayWayType() {
		return payWayType;
	}

	public void setPayWayType(Dict payWayType) {
		this.payWayType = payWayType;
	}

	public Integer getSuccessStatusId() {
		return successStatusId;
	}

	public void setSuccessStatusId(Integer successStatusId) {
		this.successStatusId = successStatusId;
	}

	public Dict getSuccessStatus() {
		return successStatus;
	}

	public void setSuccessStatus(Dict successStatus) {
		this.successStatus = successStatus;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Integer getOfflineWayTypeId() {
		return offlineWayTypeId;
	}

	public void setOfflineWayTypeId(Integer offlineWayTypeId) {
		this.offlineWayTypeId = offlineWayTypeId;
	}

	public Dict getOfflineWayType() {
		return offlineWayType;
	}

	public void setOfflineWayType(Dict offlineWayType) {
		this.offlineWayType = offlineWayType;
	}


	public PayOfflineInstallmentType getOfflineInstallmentTypeId() {
		return offlineInstallmentTypeId;
	}

	public void setOfflineInstallmentTypeId(PayOfflineInstallmentType offlineInstallmentTypeId) {
		this.offlineInstallmentTypeId = offlineInstallmentTypeId;
	}

	public Dict getOfflineInstallmentType() {
		return offlineInstallmentType;
	}

	public void setOfflineInstallmentType(Dict offlineInstallmentType) {
		this.offlineInstallmentType = offlineInstallmentType;
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

	public String getOfflineRemark() {
		return offlineRemark;
	}

	public void setOfflineRemark(String offlineRemark) {
		this.offlineRemark = offlineRemark;
	}

	public Integer getOfflineAuditStatusId() {
		return offlineAuditStatusId;
	}

	public void setOfflineAuditStatusId(Integer offlineAuditStatusId) {
		this.offlineAuditStatusId = offlineAuditStatusId;
	}

	public Dict getOfflineAuditStatus() {
		return offlineAuditStatus;
	}

	public void setOfflineAuditStatus(Dict offlineAuditStatus) {
		this.offlineAuditStatus = offlineAuditStatus;
	}

	public Integer getOfflineAddUserId() {
		return offlineAddUserId;
	}

	public void setOfflineAddUserId(Integer offlineAddUserId) {
		this.offlineAddUserId = offlineAddUserId;
	}

	public User getOfflineAddUser() {
		return offlineAddUser;
	}

	public void setOfflineAddUser(User offlineAddUser) {
		this.offlineAddUser = offlineAddUser;
	}

	public String getOnlineBankCodeId() {
		return onlineBankCodeId;
	}

	public void setOnlineBankCodeId(String onlineBankCodeId) {
		this.onlineBankCodeId = onlineBankCodeId;
	}

	public String getOnlineTradeNo() {
		return onlineTradeNo;
	}

	public void setOnlineTradeNo(String onlineTradeNo) {
		this.onlineTradeNo = onlineTradeNo;
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

	public List<OrderPayMap> getOrderPayMaps() {
		return orderPayMaps;
	}

	public void setOrderPayMaps(List<OrderPayMap> orderPayMaps) {
		this.orderPayMaps = orderPayMaps;
	}

	public PayForOrderCountType getPayForOrderCount() {
		return payForOrderCount;
	}

	public void setPayForOrderCount(PayForOrderCountType payForOrderCount) {
		this.payForOrderCount = payForOrderCount;
	}
	
}