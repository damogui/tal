package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;

public class TaskUndistributedWorkspaceTest extends TaskOpenSeaWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/customer/task/undistributed/list";
		listPartName = formPartName = "未分配任务";
		resourceNodeCode = "Operation_CRM_Task_Undistributed";
		listPartJsController = TaskAllListPart.class.getName();
		listPartServiceController = TaskAllListPart.class.getName();
		listPartImportJs = "/gsb/crm/base/js/task-base-list.part.js|/gsb/crm/platform/js/task-all-list.part.js|/gsb/gsb.custom.query.controls.js";
		listToolbarPath = "task/undistributed/list";
		//未分配条件
		listFilter = " distribut = 0";
	}

	public PToolbar createRowToolbar() {
		
		return null;
	}
}
