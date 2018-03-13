package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
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

	@Column(name = "to_order_id", header = "去向订单Id")
	private Integer toOrderId;
	
	//转入还是转出
}
