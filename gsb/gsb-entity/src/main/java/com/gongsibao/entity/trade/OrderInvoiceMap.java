package com.gongsibao.entity.trade;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "so_order_invoice_map", header = "订单和发票关系'")
public class OrderInvoiceMap extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1442220211790152099L;
	@Column(name = "order_id", header = "订单序号")
	private Integer orderId;

	@Column(name = "invoice_id", header = "发票序号")
	private Integer invoiceId;

	@Reference(foreignKey = "orderId")
	private SoOrder soOrder;
	
	@Reference(foreignKey = "invoiceId")
	private Invoice invoice;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public SoOrder getSoOrder() {
		return soOrder;
	}

	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
