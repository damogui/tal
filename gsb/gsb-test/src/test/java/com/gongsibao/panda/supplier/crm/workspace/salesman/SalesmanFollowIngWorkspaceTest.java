package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;

public class SalesmanFollowIngWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		listPartName = "跟进中商机";
		urlList = "/crm/salesman/task/following/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_FOLLOWING";
		listFilter = "foolowStatus = 3 and ownerId = '{userId}'";

        listPartServiceController = TaskAllListPart.class.getName();
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
