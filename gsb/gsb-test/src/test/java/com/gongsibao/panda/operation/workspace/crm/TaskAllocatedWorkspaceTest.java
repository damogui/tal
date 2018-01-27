package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllocatedListPart;

public class TaskAllocatedWorkspaceTest extends TaskALLWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/operation/customer/task/allocated/list";
		listPartName = formPartName = "已分配任务";
		resourceNodeCode = "Operation_CRM_Task_Allocated";
		listPartJsController = TaskAllocatedListPart.class.getName();
		listPartServiceController = TaskAllocatedListPart.class.getName();
		listPartImportJs = "/gsb/crm/base/js/task-base-list.part.js|/gsb/crm/platform/js/task-allocated-list.part.js";
		listFilter = "(owner_id is not null and owner_id >0)";
		listToolbarPath = "task/allocated/list";
	}
	
	public PToolbar createRowToolbar() {
		
		return null;
	}
}