package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.panda.crm.workspace.salesman.SalesmanAllTaskWorkspaceTest;

public class DepartmentDefeatedWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/defeated/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_DEFEATED";
//		listFilter = "foolowStatus = 4 and creator_id = '{userId}'";
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
