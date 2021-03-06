package com.gongsibao.franchisee.web;

import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.entity.IPersistable;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.dic.FranchiseeAllotStatus;

public class MyFranchiseeFormPart extends FranchiseeFormPart {

	public IPersistable newInstance(Object par) {
		
		Franchisee entity = (Franchisee)super.newInstance(par);
		entity.setOwnerId(entity.getCreatorId());
		
		UserPermission up = UserPermissionManager.getUserPermission();
		Integer departmentId = up.getEmployee().getDepartmentId();
		entity.setDepartmentId(departmentId);
		entity.setAllotStatus(FranchiseeAllotStatus.ALLOCATED);
		return entity; 
	}
}
