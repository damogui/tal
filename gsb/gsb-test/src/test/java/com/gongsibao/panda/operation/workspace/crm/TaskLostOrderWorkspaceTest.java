package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.TaskLostOrderListPart;

public class TaskLostOrderWorkspaceTest extends TaskOpenSeaWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/customer/task/lostorder/list";
		listPartName = formPartName = "无法签单";
		resourceNodeCode = "GSB_CRM_Customer_Manager_Task_Lost_Order";
		listPartJsController = TaskLostOrderListPart.class.getName();
		listPartServiceController = TaskLostOrderListPart.class.getName();
		listPartImportJs = "/gsb/crm/base/js/task-base-list.part.js|/gsb/crm/platform/js/task-lostorder-list.part.js";
		listToolbarPath = null;
		rowToolbaPath = "/operation/task/lostOrder/toolbar";
		listFilter = "foolowStatus = 4";
	}

	public PToolbar createListToolbar() {

		return null;
	}

	public PToolbar createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(rowToolbaPath);
			toolbar.setName("抽查");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("verified");
			item.setName("属实");
			item.setSeq(1000);
			item.setCommand("{controller}.verified();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("untrue");
			item.setName("不属实");
			item.setSeq(2000);
			item.setCommand("{controller}.untrue();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("submitRemark");
			item.setName("备注");
			item.setSeq(3000);
			item.setCommand("{controller}.submitRemark();");
			toolbar.getItems().add(item);
		}
		return toolbar;
	}
}