package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

public class DepartmentFollowIngWorkspaceTest extends DepartmentAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/following/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_FOLLOWING";
//		listFilter = "foolowStatus = 3 and creator_id = '{userId}'";
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