package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.panda.crm.workspace.salesman.SalesmanAllTaskWorkspaceTest;

public class DepartmentUnStartWorkspaceTest extends SalesmanAllTaskWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/crm/department/unstart/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_START";
		listFilter = "creator_id = '{userId}' and foolow_status is NULL and intention_category is NULL";
		;
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
