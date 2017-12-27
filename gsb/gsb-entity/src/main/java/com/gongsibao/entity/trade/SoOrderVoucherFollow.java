package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="so_order_voucher_follow",header="手动订单凭证跟进记录")
public class SoOrderVoucherFollow extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7414203545566669647L;
	
	@Column(name="content",header="跟进内容")
	private String content;
	
	@Column(name="order_id",header="订单id")
	private Integer orderId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
	
	
}

