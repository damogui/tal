package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

public class DepartmentTimeOutWorkspaceTest extends DepartmentAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		super.setup();
		urlList = "/crm/department/task/timeout/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_TIMEOUT";

		//当前登录人所在部门的子部门,需要扩展
		listFilter = "foolowStatus = 3 and ownerId = '{userId}' and nextFoolowTime>NOW()";
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