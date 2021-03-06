package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;
import com.gongsibao.crm.web.platform.PlatformTaskAllListPart;

public class TaskUndistributedWorkspaceTest extends TaskOpenSeaWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/customer/task/undistributed/list";
		listPartName = formPartName = "未分配商机";
		resourceNodeCode = "Operation_CRM_Task_Undistributed";
		listPartJsController = TaskAllListPart.class.getName();
		listPartServiceController = PlatformTaskAllListPart.class.getName();
		listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listToolbarPath = "task/undistributed/list";
		//未分配条件
		listFilter = "(distribut is NULL or distribut = 0)";
	}

	public PToolbar createRowToolbar() {
		
		return null;
	}
}
