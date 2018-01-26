package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.panda.crm.workspace.salesman.SalesmanAllTaskWorkspaceTest;

public class DepartmentCheckAbnormalWorkspaceTest extends SalesmanAllTaskWorkspaceTest{
	
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/check/abnormal/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_CHECK_ABNORMAL";
//		listFilter = "foolowStatus=5 and ownerId = '{userId}'";
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
