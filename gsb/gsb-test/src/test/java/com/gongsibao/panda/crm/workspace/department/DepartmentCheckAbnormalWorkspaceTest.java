package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DepartmentCheckAbnormalWorkspaceTest extends DepartmentAllTaskWorkspaceTest{
	
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/check/abnormal/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_CHECK_ABNORMAL";
		
		//当前登录人所在部门的子部门,需要扩展
		listFilter = "inspectionState = 2 and foolowStatus= 4";
		
		listToolbarPath = "department/task/abnormal";
		rowToolbaPath = "department/task/abnormal/row";
	}
	
	@Override
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("抽查异常工具栏");
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
			toolbar.setName("异常处理工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "dispose", "处理", "fa fa-edit", "dispose()", null, 6);
		return toolbar;
	}
	
}
