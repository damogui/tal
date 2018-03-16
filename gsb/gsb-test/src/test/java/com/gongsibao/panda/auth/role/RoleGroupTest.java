package com.gongsibao.panda.auth.role;

import java.sql.Types;

import org.junit.Test;
import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IRoleGroupService;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.util.StringManager;

/**
 * @author hw 预置角色分组
 */
public class RoleGroupTest {

	IRoleGroupService roleGroupService = ServiceFactory.create(IRoleGroupService.class);

	@Test
	public void run() {

		this.create("Platform", "平台");
		this.create("Platform_PreSales", "售前", "Platform");
		this.create("Platform_SCRUM", "SCRUM", "Platform");
		this.create("Platform_Franchisee", "招商CRM", "Platform");
		this.create("Platform_Ma", " 股转中心", "Platform");
		this.create("Platform_Finance", "财务", "Platform");
		this.create("Platform_Operation", "运营", "Platform");

		this.create("Supplier", "服务商");
		this.create("Supplier_CRM", "客户管理", "Supplier");
		this.create("Supplier_Order", "订单管理 ", "Supplier");
		this.create("Supplier_IDuty", "订单流程", "Supplier");
		this.create("Supplier_Trademark", "商标管理", "Supplier");
		this.create("Supplier_Taurus", "金牛座", "Supplier");
		
		ICatEntityService catService = ServiceFactory.create(ICatEntityService.class);
		catService.generatePathCode(RoleGroup.class.getName());
	}

	private void create(String code, String name) {

		this.create(code, name, null);
	}

	/**
	 * @Title: create
	 * @Description: TODO(创建角色分组)
	 * @param: @param code
	 * @return: void
	 * @throws
	 */
	private void create(String code, String name, String parentCode) {

		RoleGroup entity = this.byCode(code);
		if (entity == null) {

			entity = new RoleGroup();
			entity.toNew();
		}
		if (!StringManager.isNullOrEmpty(parentCode)) {

			RoleGroup parent = this.byCode(parentCode);
			if (parent != null) {

				entity.setParentId(parent.getId());
			}
		}

		entity.setCode(code);
		entity.setName(name);

		roleGroupService.save(entity);
	}

	/**
	 * @Title: byCode
	 * @Description: TODO(角色分组是否存在)
	 * @param: @param code
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	private RoleGroup byCode(String code) {

		Oql oql = new Oql();
		{
			oql.setType(RoleGroup.class);
			oql.setSelects("*");
			oql.setFilter("code=?");
			oql.getParameters().add("@code", code, Types.VARCHAR);
		}

		RoleGroup group = roleGroupService.queryFirst(oql);
		return group;
	}
}
