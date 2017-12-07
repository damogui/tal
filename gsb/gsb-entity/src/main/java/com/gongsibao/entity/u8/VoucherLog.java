package com.gongsibao.entity.u8;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.u8.dic.VoucherLogType;

@Table(name = "u8_voucher_log")
public class VoucherLog extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3759007600085440410L;
	
	@Column(name = "sender", header = "发送方编码（eai配置系统注册码）")
	private String sender;

	@Column(name = "set_of_books_id", header = "账套id")
	private Integer setOfBooksId;
	
	@Reference(foreignKey="setOfBooksId")
	private SetOfBooks setOfBooks;

	@Column(name = "order_no", header = "订单编号")
	private String orderNo;

	@Column(name = "pay_id", header = "支付编号")
	private Integer payId;
	
	@Column(name = "refund_id", header = "退单退款编号")
	private Integer refundId;

	@Column(name = "in_voucher_log_id", header = "当生成确认收入凭证时，关联的收款凭证的记录id(自连id)")
	private Integer inVoucherLogId;

	@Column(name = "voucher_id", header = "u8生成的凭证号")
	private String voucherId;

	@Column(name = "dsc", header = "u8返回信息说明")
	private String dsc;
	
	@Column(name = "succeed", header = "u8返回成功标识：0：成功；非0：失败")
	private String succeed;

	@Column(name = "refund_item_id", header = "退产品单退款编号")
	private Integer refundItemId;
	
	@Column(name = "refund_item_price_id", header = "退产品服务项单退款编号")
	private Integer refundItemPriceId;

	@Column(name = "xml_param", header = "传入的参数")
	private String xmlParam;

	@Column(name = "xml_return", header = "返回值")
	private String xmlReturn;
	
	@Column(name = "type", header = "0:收款 1:确认收入 2:退款")
	private VoucherLogType type = VoucherLogType.Shoukuan;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getSetOfBooksId() {
		return setOfBooksId;
	}

	public void setSetOfBooksId(Integer setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getRefundId() {
		return refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	public Integer getInVoucherLogId() {
		return inVoucherLogId;
	}

	public void setInVoucherLogId(Integer inVoucherLogId) {
		this.inVoucherLogId = inVoucherLogId;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public Integer getRefundItemId() {
		return refundItemId;
	}

	public void setRefundItemId(Integer refundItemId) {
		this.refundItemId = refundItemId;
	}

	public Integer getRefundItemPriceId() {
		return refundItemPriceId;
	}

	public void setRefundItemPriceId(Integer refundItemPriceId) {
		this.refundItemPriceId = refundItemPriceId;
	}

	public String getXmlParam() {
		return xmlParam;
	}

	public void setXmlParam(String xmlParam) {
		this.xmlParam = xmlParam;
	}

	public String getXmlReturn() {
		return xmlReturn;
	}

	public void setXmlReturn(String xmlReturn) {
		this.xmlReturn = xmlReturn;
	}

	public VoucherLogType getType() {
		return type;
	}

	public void setType(VoucherLogType type) {
		this.type = type;
	}
	
	

	
	
}
