package com.gongsibao.panda.auth.role;

import java.sql.Types;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.entity.Role;

import com.gongsibao.entity.supplier.FunctionModule;
import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.supplier.base.IFunctionModuleRoleService;
import com.gongsibao.supplier.base.IFunctionModuleService;

public class FunctionTest {

	IRoleService roleService = ServiceFactory.create(IRoleService.class);
	IFunctionModuleService functionModuleService = ServiceFactory.create(IFunctionModuleService.class);
	IFunctionModuleRoleService functionModuleRoleService = ServiceFactory.create(IFunctionModuleRoleService.class);

	@Test
	public void run() {

		this.create("CRM", "客户管理");
		this.createFunctionModuleRole("CRM", "Supplier_Admin");
		this.createFunctionModuleRole("CRM", "Supplier_Leader");
		this.createFunctionModuleRole("CRM", "Supplier_Salesman");

		this.create("Order", "订单管理");
		this.createFunctionModuleRole("Order", "Supplier_Order_Admin");
		this.createFunctionModuleRole("Order", "Supplier_Order_Leader");
		this.createFunctionModuleRole("Order", "Supplier_Order_Salesman");

		this.create("IGIRL", "IGIRL");
		this.createFunctionModuleRole("IGIRL", "IGIRL_Admin");
		this.createFunctionModuleRole("IGIRL", "IGIRL_Leader");
		this.createFunctionModuleRole("IGIRL", "IGIRL_Salesman");
		this.createFunctionModuleRole("IGIRL", "IGIRL_Audit");
	}

	private void create(String code, String name) {

		FunctionModule entity = this.byCode(code);
		if (entity == null) {

			entity = new FunctionModule();
			entity.toNew();
		}
		entity.setCode(code);
		entity.setName(name);
		functionModuleService.save(entity);
	}

	private void createFunctionModuleRole(String functionCode, String roleCode) {

		FunctionModule fModule = this.byCode(functionCode);
		if (fModule == null) {
			System.err.println("功能模块：" + functionCode + "不存在");
		}

		Role role = this.getRoleByCode(roleCode);
		if (role == null) {
			System.err.println("角色：" + roleCode + "不存在");
		}

		FunctionModuleRole entity = this.getFunctionModuleRole(fModule.getId(), role.getId());
		if (entity == null) {

			entity = new FunctionModuleRole();
			entity.toNew();
			entity.setFunctionModuleId(fModule.getId());
			entity.setRoleId(role.getId());
			functionModuleRoleService.save(entity);
		}
	}

	private FunctionModuleRole getFunctionModuleRole(Integer functionId, Integer roleId) {

		Oql oql = new Oql();
		{
			oql.setType(FunctionModuleRole.class);
			oql.setSelects("*");
			oql.setFilter("functionModuleId=? and roleId=?");
			oql.getParameters().add("@functionModuleId", functionId, Types.INTEGER);
			oql.getParameters().add("@roleId", roleId, Types.INTEGER);
		}

		FunctionModuleRole fmr = functionModuleRoleService.queryFirst(oql);
		return fmr;
	}

	private Role getRoleByCode(String code) {

		Oql oql = new Oql();
		{
			oql.setType(Role.class);
			oql.setSelects("*");
			oql.setFilter("code=?");
			oql.getParameters().add("@code", code, Types.VARCHAR);
		}

		Role group = roleService.queryFirst(oql);
		return group;
	}

	private FunctionModule byCode(String code) {

		Oql oql = new Oql();
		{
			oql.setType(FunctionModule.class);
			oql.setSelects("*");
			oql.setFilter("code=?");
			oql.getParameters().add("@code", code, Types.VARCHAR);
		}

		FunctionModule group = functionModuleService.queryFirst(oql);
		return group;
	}

}
