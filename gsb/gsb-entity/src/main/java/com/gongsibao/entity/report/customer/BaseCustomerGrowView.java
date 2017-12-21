package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.entity.Persistable;

public abstract class BaseCustomerGrowView extends Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*id必须存在否者运行失败*/
	@Id
	@Auto
	private Integer id;
	private Long newCustomer;
	private Long newShareCustomer;
	private Long userId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(Long newCustomer) {
		this.newCustomer = newCustomer;
	}
	public Long getNewShareCustomer() {
		return newShareCustomer;
	}
	public void setNewShareCustomer(Long newShareCustomer) {
		this.newShareCustomer = newShareCustomer;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
