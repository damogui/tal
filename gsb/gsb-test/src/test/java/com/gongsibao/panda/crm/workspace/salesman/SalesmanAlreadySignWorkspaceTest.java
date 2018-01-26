package com.gongsibao.panda.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

public class SalesmanAlreadySignWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		super.setup();
		urlList = "/crm/salesman/task/signed/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_SIGNED";
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
