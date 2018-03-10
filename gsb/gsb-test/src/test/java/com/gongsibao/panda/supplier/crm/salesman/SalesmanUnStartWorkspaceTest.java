package com.gongsibao.panda.supplier.crm.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

public class SalesmanUnStartWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "未启动任务";
		urlList = "/crm/salesman/task/unstart/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_START";
		listFilter = "foolowStatus = 6 and ownerId = '{userId}'";
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
