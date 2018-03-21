package com.gongsibao.supplier.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.supplier.FunctionModuleRole;

public interface IFunctionModuleRoleService extends IPersistableService<FunctionModuleRole>{

	 List<FunctionModuleRole> queryList(List<Integer> moduleIdList);
}
