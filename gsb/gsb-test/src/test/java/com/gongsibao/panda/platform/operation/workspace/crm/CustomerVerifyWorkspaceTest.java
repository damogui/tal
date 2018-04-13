package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.NCustomerVerifyListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerVerifyWorkspaceTest extends WorkspaceCreationBase{

	String rowToolbar = "platform/customer/verify";
	
	@Before
	public void setup() {

		entity = NCustomer.class;
		urlList = "/crm/platform/customer/verify";
		listToolbarPath = "platform/customer/verify/edit";
		listPartName = formPartName = "校验客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_Add";
		listPartImportJs = "/gsb/platform/operation/crm/js/customer-verify-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listPartJsController = NCustomerVerifyListPart.class.getName();
		listPartServiceController = NCustomerVerifyListPart.class.getName();
	}
	
	@Test
	public void createListToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType otView = operationTypeService.byCode(OperationTypes.view);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("客户校验");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setName("创建新客户");
			item.setOperationType(otView);
			item.setCommand("{controller}.add()");
			toolbar.getItems().add(item);
		}
		
		toolbarService.save(toolbar);
	}

	@Test
	public void createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType otView = operationTypeService.byCode(OperationTypes.view);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(rowToolbar);
			toolbar.setName("客户校验");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("binding");
			item.setName("绑定");
			item.setSeq(5000);
			item.setOperationType(otView);
			item.setCommand("{controller}.binding()");
			toolbar.getItems().add(item);
		}
		
		toolbarService.save(toolbar);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setAutoQuery(false);
			datagrid.setResourceNode(node);
			datagrid.setName("客户校验列表");
			datagrid.setToolbar(rowToolbar);
		}

		//PDatagridColumn column = null;
		addColumn(datagrid, "creator", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "客户Id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "realName", "名称", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "mobile", "手机号码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "telephone", "座机", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);{
			item.setWidth(400);
			item.setTooltip("手机号 / 座机 / QQ / 微信");
			item.setRequired(true);
		}
		return queryProject;
	}


	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
	}
}
