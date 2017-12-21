package com.gongsibao.franchisee.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.franchisee.base.IFranchiseeService;

public class FranchiseeListPart extends ListPart{

	IFranchiseeService franchiseeService = ServiceFactory.create(IFranchiseeService.class);
	
	/**
	 * @param ids
	 * @param departmentId
	 * @param ownerId
	 * @return
	 */
	public Boolean allot(String ids,Integer departmentId,Integer ownerId){
		
		String[] arr = ids.split("_");
		return franchiseeService.allot(arr, departmentId, ownerId);
	}
}
