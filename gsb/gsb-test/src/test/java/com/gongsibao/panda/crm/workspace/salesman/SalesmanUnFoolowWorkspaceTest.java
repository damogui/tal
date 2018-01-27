package com.gongsibao.panda.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

public class SalesmanUnFoolowWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "待跟进任务";
		urlList = "/crm/salesman/task/unfoolow/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_UNFOOLOW";
		
		//如：有下次跟进时间，并且是当天的
		listFilter = "nextFoolowTime is not null and nextFoolowTime = CURDATE() and ownerId = '{userId}'";
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
