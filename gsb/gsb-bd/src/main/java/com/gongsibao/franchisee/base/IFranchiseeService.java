package com.gongsibao.franchisee.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.Franchisee;

public interface IFranchiseeService extends IPersistableService<Franchisee>{
	
	/**
	 * 分配客户给业务员
	 * @param ids
	 * @param departmentId
	 * @param ownerId
	 * @return
	 */
	Boolean allot(String[] ss,Integer departmentId,Integer ownerId);
	
}
