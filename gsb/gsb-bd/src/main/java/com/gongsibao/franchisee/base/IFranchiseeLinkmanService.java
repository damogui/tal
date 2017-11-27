package com.gongsibao.franchisee.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.FranchiseeLinkman;

public interface IFranchiseeLinkmanService extends IPersistableService<FranchiseeLinkman>{

	
	List<FranchiseeLinkman> getLinkmanByFranchiseeId(Integer franchiseeId);
}
