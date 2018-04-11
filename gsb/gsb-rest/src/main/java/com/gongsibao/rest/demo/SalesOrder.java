package com.gongsibao.rest.demo;

import java.math.BigDecimal;

import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="test_order",header="测试订单")
public class SalesOrder extends BizEntity {
	
	private static final long serialVersionUID = -9031132521069429671L;
	
	private String status;
	private BigDecimal quantity;
	private BigDecimal amount;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
