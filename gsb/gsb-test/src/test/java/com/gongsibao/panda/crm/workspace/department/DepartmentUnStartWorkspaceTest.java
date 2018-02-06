package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DepartmentUnStartWorkspaceTest extends DepartmentAllTaskWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/crm/department/unstart/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_START";
		listToolbarPath = "department/task/unstart";
		
		//当前登录人所在部门的子部门,需要扩展
		listFilter = "foolowStatus = 2";
	}

	@Override
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("未启动工具栏");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "regain", "收回", "fa fa-mail-reply", "regain()", null, 6);
		addToolbarItem(toolbar, "transfer", "任务转移", "fa fa-share-square-o", "transfer()", null, 7);
		return toolbar;
	}

	@Override
	public PToolbar createRowToolbar() {

		return null;
	}
}
