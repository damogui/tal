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
		listToolbarPath = "department/task/unfoolow";
		
		//当前登录人所在部门的子部门,需要扩展
		listFilter = "nextFoolowTime is not null and nextFoolowTime = CURDATE() and ownerId = '{userId}'";
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
