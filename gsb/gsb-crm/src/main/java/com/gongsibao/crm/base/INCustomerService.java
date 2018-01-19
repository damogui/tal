package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomer;

public interface INCustomerService  extends IPersistableService<NCustomer> {

	/**
	 * 修改是否是会员
	 * @param customerId
	 * @return
	 */
	public int updateIsMember(Integer customerId);
}
