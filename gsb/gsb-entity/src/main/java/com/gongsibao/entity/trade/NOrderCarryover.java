package com.gongsibao.entity.trade;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "so_order_carryover", header = "订单结转")
public class NOrderCarryover extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 7298276107079254101L;

	@Column(name = "amount", header = "结转金额")
	private Integer amount;

	@Column(name = "form_order_id", header = "来源订单Id")
	private Integer formOrderId;

	@Reference(foreignKey = "formOrderId", header = "服务商")
	private SoOrder formOrder;

	@Column(name = "to_order_id", header = "去向订单Id")
	private Integer toOrderId;
	
	@Column(name = "form_order_no", header = "来源订单编号")
	private String formOrderNo;

	@Column(name = "to_order_no", header = "去向订单编号")
	private String toOrderNo;

	@Column(name = "audit_status", header = "结转状态")
	private AuditStatusType auditStatus = AuditStatusType.wu;

	@Column(name = "remark", size = 500, header = "结转说明")
	private String remark;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getFormOrderId() {
		return formOrderId;
	}

	public void setFormOrderId(Integer formOrderId) {
		this.formOrderId = formOrderId;
	}

	public Integer getToOrderId() {
		return toOrderId;
	}

	public void setToOrderId(Integer toOrderId) {
		this.toOrderId = toOrderId;
	}

	public String getFormOrderNo() {
		return formOrderNo;
	}

	public void setFormOrderNo(String formOrderNo) {
		this.formOrderNo = formOrderNo;
	}

	public String getToOrderNo() {
		return toOrderNo;
	}

	public void setToOrderNo(String toOrderNo) {
		this.toOrderNo = toOrderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SoOrder getFormOrder() {
		return formOrder;
	}

	public void setFormOrder(SoOrder formOrder) {
		this.formOrder = formOrder;
	}

	public AuditStatusType getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatusType auditStatus) {
		this.auditStatus = auditStatus;
	}
}
