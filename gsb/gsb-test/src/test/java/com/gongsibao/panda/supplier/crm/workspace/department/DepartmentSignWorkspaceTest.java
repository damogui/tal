package com.gongsibao.panda.supplier.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

public class DepartmentSignWorkspaceTest extends DepartmentAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/signed/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_SIGNED";

		//当前登录人所在部门的子部门,需要扩展
		listFilter = "foolowStatus=5";
	}
	
	@Override
	public PToolbar createListToolbar() {
		
		return null;
	}
	
	@Override
	public PToolbar createRowToolbar() {
		
		return null;
	}
}
