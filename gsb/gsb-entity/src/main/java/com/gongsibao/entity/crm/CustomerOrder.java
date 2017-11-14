package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.crm.dic.OrderStatus;

@Table(name="crm_customer_order",header="客户订单")
public class CustomerOrder extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3535777327092342215L;

    @Column(name="code",header="订单号")
    private String code;
    
    @Column(name="amount",header="金额")
    private String amount;
    
    @Column(name="customer_id",header="客户id")
    private Integer customerId;

    @Reference(foreignKey="customerId",header="客户")
    private Customer customer;
    
    @Column(name="status",header="订单状态")
    private OrderStatus status = OrderStatus.Submit;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
}
