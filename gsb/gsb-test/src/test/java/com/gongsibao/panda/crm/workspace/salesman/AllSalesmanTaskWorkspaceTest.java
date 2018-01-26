package com.gongsibao.panda.crm.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.panda.operation.workspace.crm.TaskALLWorkspaceTest;

public class AllSalesmanTaskWorkspaceTest extends TaskALLWorkspaceTest{

	
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/salesman/task/all/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/base/js/task-base-list.part.js");
		ss.add("/gsb/crm/platform/js/task-all-list.part.js");
		listPartImportJs = StringManager.join("|", ss);
		
		listToolbarPath = "salesman/task/all";
		rowToolbaPath = "salesman/task/all/row";
	}
	
	@Override
	public PToolbar createListToolbar() {
		
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("开通会员工具栏操作");
			toolbar.setResourceNode(node);
		}
		
		addToolbarItem(toolbar, "add", "新增任务", "fa fa-plus", "openMemberPopup()", null, 5);
		addToolbarItem(toolbar, "openMemberPopup", "开通会员", "fa fa-check", "openMemberPopup()", null, 6);
		addToolbarItem(toolbar, "taskTransferPopup", "任务转移", "fa fa-edit", "taskTransferPopup()", null, 7);
		return toolbar;
	}
	
	@Override
	public PToolbar createRowToolbar() {
		
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(rowToolbaPath);
			toolbar.setName("跟进行工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "followUpPopup", "跟进", "fa fa-edit", "followUpPopup()", null, 6);
		addToolbarItem(toolbar, "backTaskPopup", "退回", "fa fa-edit", "backTaskPopup()", null, 7);
		return toolbar;
	}
}
