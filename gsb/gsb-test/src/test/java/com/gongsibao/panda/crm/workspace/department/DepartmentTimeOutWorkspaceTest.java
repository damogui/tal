package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.panda.crm.workspace.salesman.SalesmanAllTaskWorkspaceTest;

public class DepartmentTimeOutWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		super.setup();
		urlList = "/crm/department/task/timeout/list";
		resourceNodeCode = "CRM_DEPARTMENT_TIMEOUT";
		listFilter = "foolowStatus = 2 and ownerId = '{userId}'";
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