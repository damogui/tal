package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DepartmentUnFoolowWorkspaceTest extends DepartmentAllTaskWorkspaceTest{
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/unfoolow/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_UNFOOLOW";
		listFilter = "foolowStatus = 2 and creator_id = '{userId}'";
		listToolbarPath = "department/task/unfoolow";
	}
	

	@Override
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("待跟进工具栏");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "regain", "收回", "fa fa-check", "regain()", null, 6);
		addToolbarItem(toolbar, "transfer", "任务转移", "fa fa-share-square-o", "transfer()", null, 7);
		return toolbar;
	}
	
	@Override
	public PToolbar createRowToolbar() {
		
		return null;
	}
}
