package com.gongsibao.panda.supplier.crm.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DepartmentHighSeasWorkspaceTest extends DepartmentAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/highseas/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_HIGHSEAS";
		listToolbarPath = "department/task/highseas";
		
		//当前登录人所在部门的子部门,需要扩展,有所属部门，但没有业务员
		listFilter = "(owner_id is null or owner_id=0)";
	}
	
	@Override
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("公海工具栏");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "batchAllocation", "批量分配", "fa fa-check", "batchAllocation()", null, 5);
		
		//addToolbarItem(toolbar, "regain", "退回", "fa fa-mail-reply", "rollback()", null, 6);
		return toolbar;
	}
	
	@Override
	public PToolbar createRowToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(rowToolbaPath);
			toolbar.setName("跟进行工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "rollback", "退回", "fa fa-edit", "rollback()", null, 6);
		return toolbar;
		//return null;
	}
}
