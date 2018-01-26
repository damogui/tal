package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskUndistributedListPart;

public class TaskUndistributedWorkspaceTest extends TaskOpenSeaWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/customer/task/undistributed/list";
		listPartName = formPartName = "未分配任务";
		resourceNodeCode = "GSB_CRM_Customer_Manager_Task_Undistributed";
		listFilter = "foolow_status in(1)";
		listPartJsController = TaskUndistributedListPart.class.getName();
		listPartServiceController = TaskUndistributedListPart.class.getName();
		listPartImportJs = "/gsb/crm/base/js/task-base-list.part.js|/gsb/crm/platform/js/task-undistributed-list.part.js";
		listToolbarPath = "task/undistributed/list";
	}

	public PToolbar createRowToolbar() {
		
		return null;
	}
}
