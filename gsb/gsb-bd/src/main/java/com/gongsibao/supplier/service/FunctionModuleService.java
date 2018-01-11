package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.FunctionModule;
import com.gongsibao.supplier.base.IFunctionModuleService;

@Service
public class FunctionModuleService extends PersistableService<FunctionModule> implements IFunctionModuleService {

	public FunctionModuleService() {
		super();
		this.type = FunctionModule.class;
	}
}