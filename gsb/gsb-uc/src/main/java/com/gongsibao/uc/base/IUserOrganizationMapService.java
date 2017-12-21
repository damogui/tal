package com.gongsibao.uc.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.uc.UserOrganizationMap;

public interface IUserOrganizationMapService extends IPersistableService<UserOrganizationMap> {
	
	List<UserOrganizationMap> getMapList(Integer departmentId);
}