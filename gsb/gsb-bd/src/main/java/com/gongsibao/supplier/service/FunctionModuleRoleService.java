package com.gongsibao.supplier.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.supplier.base.IFunctionModuleRoleService;

@Service
public class FunctionModuleRoleService extends PersistableService<FunctionModuleRole> implements IFunctionModuleRoleService {

	public FunctionModuleRoleService() {
		super();
		this.type = FunctionModuleRole.class;
	}

	@Override
	public List<FunctionModuleRole> queryList(List<Integer> moduleIdList) {

		String moduleIds = StringManager.join(",", moduleIdList);
		Oql oql = new Oql();
		{
			oql.setType(FunctionModuleRole.class);
			oql.setSelects("*");
			oql.setFilter("function_module_id in (" + moduleIds + ")");
		}
		return this.queryList(oql);
	}
}