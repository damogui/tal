package com.gongsibao.panda.platform.operation.workspace.supplier.data;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IRoleGroupService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.supplier.FunctionModule;
import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.supplier.base.IFunctionModuleRoleService;
import com.gongsibao.supplier.base.IFunctionModuleService;

/**
 * Created by win on 2018/1/25. 预设角色(sys_permission_role
 * Role)和功能(sp_function_module)表
 */
public class ImportTaskRoleAndFunctionTest {

	IRoleGroupService serviceRoleGroupService = ServiceFactory.create(IRoleGroupService.class);// 角色分组
	IRoleService serviceRoleService = ServiceFactory.create(IRoleService.class);// 角色
	IFunctionModuleService serviceFunctionModuleService = ServiceFactory.create(IFunctionModuleService.class);// 功能模块
	IFunctionModuleRoleService serviceFunctionModuleRoleService = ServiceFactory.create(IFunctionModuleRoleService.class);// 功能角色明细

	@Test
	public void run() {

		RoleGroup entityRoleGroup = new RoleGroup();
		{
			entityRoleGroup.setCode("Supplier");
			entityRoleGroup.setName("服务商");

		}

		// 角色分组beg
		IPersister<RoleGroup> pmRoleGroup = PersisterFactory.create();
		String sqlRoleGroup = "SELECT  id FROM  sys_permission_role_group   WHERE code=? ;";
		QueryParameters qsRoleGroup = new QueryParameters();
		qsRoleGroup.add("@code", entityRoleGroup.getCode(), Types.VARCHAR);
		Object numObjectRoleGroup = pmRoleGroup.executeScalar(sqlRoleGroup, qsRoleGroup);

		if (numObjectRoleGroup == null) {// 添加
			entityRoleGroup.toNew();
			serviceRoleGroupService.save(entityRoleGroup);

		} else {// 修改

			int numCount = Integer.parseInt(numObjectRoleGroup.toString());
			entityRoleGroup.setId(numCount);
			entityRoleGroup.toPersist();
			serviceRoleGroupService.save(entityRoleGroup);

		}
		// 角色分组end
		Role entityRole1 = new Role();
		{

			entityRole1.setCode("Supplier_Admin");
			entityRole1.setName("管理员");
			entityRole1.setWorkbenchPtah("/panda/supplier/workbench");
			entityRole1.setRoleGroupId(entityRoleGroup.getId());

		}
		Role entityRole2 = new Role();
		{

			entityRole2.setCode("Supplier_Leader");
			entityRole2.setName("部门负责人");
			entityRole2.setWorkbenchPtah("/panda/supplier/workbench");
			entityRole2.setRoleGroupId(entityRoleGroup.getId());

		}
		Role entityRole3 = new Role();
		{

			entityRole3.setCode("Supplier_Salesman");
			entityRole3.setName("业务员");
			entityRole3.setWorkbenchPtah("/panda/supplier/workbench");
			entityRole3.setRoleGroupId(entityRoleGroup.getId());

		}

		List<Role> listRole = new ArrayList<Role>();
		listRole.add(entityRole1);
		listRole.add(entityRole2);
		listRole.add(entityRole3);
		// 角色beg
		IPersister<Role> pmRole = PersisterFactory.create();
		String sqlRole = "SELECT  id FROM  sys_permission_role   WHERE code=? ;";
		for (Role item : listRole) {

			QueryParameters qsRole = new QueryParameters();
			qsRole.add("@code", item.getCode(), Types.VARCHAR);

			Object numObject = pmRole.executeScalar(sqlRole, qsRole);

			if (numObject == null) {// 添加
				item.toNew();
				serviceRoleService.save(item);

			} else {// 修改

				int numCount = Integer.parseInt(numObject.toString());
				item.setId(numCount);
				item.toPersist();
				serviceRoleService.save(item);

			}

		}
		
		
		
		
		
		// 角色end
		FunctionModule entityFunctionModule = new FunctionModule();
		{

			entityFunctionModule.setCode("CRM");
			entityFunctionModule.setName("客户管理");

		}

		// 功能模块beg
		IPersister<FunctionModule> pmFunctionModule = PersisterFactory.create();
		String sqlFunctionModule = "SELECT  id FROM  sp_function_module   WHERE code=? ;";
		QueryParameters qsFunctionModule = new QueryParameters();
		qsFunctionModule.add("@code", entityFunctionModule.getCode(), Types.VARCHAR);
		Object numObjectFunctionModule = pmFunctionModule.executeScalar(sqlFunctionModule, qsFunctionModule);

		
		
		if (numObjectFunctionModule == null) {// 添加
			entityFunctionModule.toNew();
			serviceFunctionModuleService.save(entityFunctionModule);

		} else {// 修改

			int numCount = Integer.parseInt(numObjectFunctionModule.toString());
			entityFunctionModule.setId(numCount);
			entityFunctionModule.toPersist();
			serviceFunctionModuleService.save(entityFunctionModule);

		}
		// 功能模块end
		FunctionModuleRole entityFunctionModuleRole1 = new FunctionModuleRole();
		{
			entityFunctionModuleRole1.setFunctionModuleId(entityFunctionModule.getId());
			entityFunctionModuleRole1.setRoleId(entityRole1.getId());

		}
		FunctionModuleRole entityFunctionModuleRole2 = new FunctionModuleRole();
		{
			entityFunctionModuleRole2.setFunctionModuleId(entityFunctionModule.getId());
			entityFunctionModuleRole2.setRoleId(entityRole2.getId());

		}
		FunctionModuleRole entityFunctionModuleRole3 = new FunctionModuleRole();
		{
			entityFunctionModuleRole3.setFunctionModuleId(entityFunctionModule.getId());
			entityFunctionModuleRole3.setRoleId(entityRole3.getId());

		}

		List<FunctionModuleRole> listFunctionModuleRole = new ArrayList<FunctionModuleRole>();
		listFunctionModuleRole.add(entityFunctionModuleRole1);
		listFunctionModuleRole.add(entityFunctionModuleRole2);
		listFunctionModuleRole.add(entityFunctionModuleRole3);

		// 功能模块明细beg
		IPersister<FunctionModuleRole> pmFunctionModuleRole = PersisterFactory.create();
		String sqlFunctionModuleRole = "SELECT  id FROM  sp_function_module_role   WHERE function_module_id=?  and " + "role_id=?;";
		for (FunctionModuleRole item : listFunctionModuleRole) {

			QueryParameters qs = new QueryParameters();
			qs.add("@function_module_id", item.getFunctionModuleId(), Types.INTEGER);
			qs.add("@role_id", item.getRoleId(), Types.INTEGER);

			Object numObject = pmFunctionModuleRole.executeScalar(sqlFunctionModuleRole, qs);

			if (numObject == null) {// 添加
				item.toNew();
				serviceFunctionModuleRoleService.save(item);

			} else {// 修改

				int numCount = Integer.parseInt(numObject.toString());
				item.setId(numCount);
				item.toPersist();
				serviceFunctionModuleRoleService.save(item);

			}

		}
		// 功能模块明细bend
	}
}
