package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAnomalyDetectionListPart;

public class TaskAnomalyDetectionWorkspaceTest extends TaskOpenSeaWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/operation/customer/task/anomalydetection/list";
		listPartName = formPartName = "抽查异常";
		resourceNodeCode = "Operation_CRM_Task_Anomaly_Detection";
		listFilter="inspection_state = 3";	
		listPartJsController = TaskAnomalyDetectionListPart.class.getName();
		listPartServiceController = TaskAnomalyDetectionListPart.class.getName();
		listPartImportJs = "/gsb/crm/base/js/task-base-list.part.js|/gsb/crm/platform/js/task-anomalydetection-list.part.js";
		listToolbarPath = "task/anomalydetection/list";
		rowToolbaPath = "panda/datagrid/row/edit";
	}

	public PToolbar createRowToolbar() {
		
		return null;
	}
}