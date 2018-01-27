package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DepartmentDefeatedWorkspaceTest extends DepartmentAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/defeated/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_DEFEATED";
		listToolbarPath = "department/task/defeated";
		rowToolbaPath = "department/task/defeated/row";
		
		//当前登录人所在部门的子部门,需要扩展
		listFilter = "foolowStatus = 4";
	}
	
	@Override
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("无法签单工具栏");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "regain", "收回", "fa fa-mail-reply", "regain()", null, 6);
		return toolbar;
	}
	
	@Override
	public PToolbar createRowToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(rowToolbaPath);
			toolbar.setName("抽查工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "verified", "属实", "fa fa-edit", "verified()", null, 6);
		addToolbarItem(toolbar, "untrue", "不属实", "fa fa-edit", "untrue()", null, 7);
		addToolbarItem(toolbar, "remark", "备注", "fa fa-edit", "submitRemark()", null, 8);
		return toolbar;
	}
}
