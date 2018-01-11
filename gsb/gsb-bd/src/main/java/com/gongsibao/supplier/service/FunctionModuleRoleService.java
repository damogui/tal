package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.supplier.base.IFunctionModuleRoleService;

@Service
public class FunctionModuleRoleService extends PersistableService<FunctionModuleRole> implements IFunctionModuleRoleService {

	public FunctionModuleRoleService() {
		super();
		this.type = FunctionModuleRole.class;
	}
}