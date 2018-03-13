package com.gongsibao.entity.trade;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "so_order_stage", header = "分期付款")
public class NOrderStage extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 3716764363463405540L;

	@Column(name = "order_id", header = "订单")
	private Integer orderId = 0;

	@JsonIgnore
	@Reference(foreignKey = "orderId", header = "销售订单", primaryKey = "pkid")
	private SoOrder soOrder;
	
	@Column(name = "instalment_index", header = "第*期")
	private Integer instalmentIndex  = 0;

	@Column(name = "percentage", header = "百分比")
	private Double percentage = 0D;

	@Column(name = "amount", header = "金额")
	private Integer amount = 0;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public SoOrder getSoOrder() {
		return soOrder;
	}

	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}

	public Integer getInstalmentIndex() {
		return instalmentIndex;
	}

	public void setInstalmentIndex(Integer instalmentIndex) {
		this.instalmentIndex = instalmentIndex;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
