package com.gongsibao.panda.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;


public class SalesmanCheckAbnormalWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "抽查异常任务";
		urlList = "/crm/salesman/check/abnormal/list";
		resourceNodeCode = "CRM_SALESMAN_CHECK_ABNORMAL";
		listFilter = "inspectionState = 2 and foolowStatus=4 and ownerId = '{userId}'";
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