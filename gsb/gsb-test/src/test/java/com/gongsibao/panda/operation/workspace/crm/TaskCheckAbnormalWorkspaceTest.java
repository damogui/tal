package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;

public class TaskCheckAbnormalWorkspaceTest extends TaskOpenSeaWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/operation/customer/task/anomalydetection/list";
		listPartName = formPartName = "抽查异常";
		resourceNodeCode = "Operation_CRM_Task_Anomaly_Detection";
		listFilter="inspectionState in (3,4)";	
		listPartJsController = TaskAllListPart.class.getName();
		listPartServiceController = TaskAllListPart.class.getName();
		listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listToolbarPath = "task/anomalydetection/list";
		rowToolbaPath = "panda/datagrid/row/edit";
	}

	public PToolbar createRowToolbar() {
		
		return null;
	}
}