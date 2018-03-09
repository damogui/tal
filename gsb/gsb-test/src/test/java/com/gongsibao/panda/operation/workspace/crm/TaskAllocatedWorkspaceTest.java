package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.TaskAllListPart;

public class TaskAllocatedWorkspaceTest extends TaskALLWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/operation/customer/task/allocated/list";
		listPartName = formPartName = "已分配任务";
		resourceNodeCode = "Operation_CRM_Task_Allocated";
		listPartJsController = TaskAllListPart.class.getName();
		listPartServiceController = TaskAllListPart.class.getName();
		listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listFilter = "(owner_id is not null and owner_id >0)";
		listToolbarPath = "task/allocated/list";
		
		rowToolbaPath ="/operation/task/allocated/row/toolbar";
	}
	
	public PToolbar createListToolbar() {
		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("新增客户");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addCustomer");
			item.setIcon("fa fa-user-plus fa-fw");
			item.setName("新增客户");
			item.setOperationType(ot1);
			item.setSeq(1);
			item.setCommand("{controller}.addCustomer();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus fa-fw");
			item.setName("新增任务");
			item.setOperationType(ot1);
			item.setSeq(2);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("takeBack");
			item.setIcon("fa fa-mail-reply");
			item.setName("批量转移");
			item.setSeq(1);
			item.setCommand("{controller}.batchTransfer();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("takeBack");
			item.setIcon("fa fa-mail-reply");
			item.setName("收回");
			item.setSeq(1);
			item.setCommand("{controller}.regain();");
			toolbar.getItems().add(item);
		}
		return toolbar;
	}
	
	public PToolbar createRowToolbar() {
		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(rowToolbaPath);
			toolbar.setName("转移");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("transfer");
			item.setName("转移");
			item.setSeq(1000);
			item.setCommand("{controller}.transfer();");
			toolbar.getItems().add(item);
		}
		return toolbar;
	}
	
}