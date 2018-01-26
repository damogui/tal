package com.gongsibao.panda.crm.workspace.department;

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
//		listFilter = "foolowStatus = 2 and creator_id = '{userId}'";
		
		listToolbarPath = "department/task/highseas";
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
		addToolbarItem(toolbar, "regain", "收回", "fa fa-mail-reply", "regain()", null, 6);
		return toolbar;
	}
	
	@Override
	public PToolbar createRowToolbar() {
		
		return null;
	}
}
