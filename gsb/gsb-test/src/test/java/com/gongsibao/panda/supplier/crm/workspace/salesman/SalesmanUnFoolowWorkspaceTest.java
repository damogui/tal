package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;

public class SalesmanUnFoolowWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "待跟进商机";
		urlList = "/crm/salesman/task/unfoolow/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_UNFOOLOW";
		
		//如：有下次跟进时间，并且是当天的
		//listFilter = "nextFoolowTime is not null and DATE_FORMAT(nextFoolowTime,'%Y-%m-%d') = CURDATE() and ownerId = '{userId}'";
		listFilter = "nextFoolowTime is not null and nextFoolowTime = CURDATE() and ownerId = '{userId}'";
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
