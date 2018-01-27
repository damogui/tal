package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerALLWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/all/list";// 列表的url
		listPartName = formPartName = "全部客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_ALL";
		listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/crm/platform/js/customer-all-list.part.js";

		listPartJsController = NCustomerAllListPart.class.getName();
		listPartServiceController = NCustomerAllListPart.class.getName();
		
		listToolbarPath = "crm/operation/customer/edit";
	}
	
	@Test
	public void createListToolbar() {
		
		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("客户列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-user-plus");
			item.setName("新增客户");
			item.setCommand(null);
			item.setSeq(1);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addTask");
			item.setIcon("fa fa-plus");
			item.setName("新增任务");
			item.setCommand(null);
			item.setSeq(2);
			item.setCommand("{controller}.addTask();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("客户列表");
			datagrid.setToolbar("panda/datagrid/row/edit");
		}

		//PDatagridColumn null;
		addColumn(datagrid, "updatorId", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "id", "客户ID", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "realName", "客户名称", ControlTypes.TEXT_BOX, 120);
		
		//公司名称
		addColumn(datagrid, "isMember", "是否会员", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "mobile", "手机号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "telephone", "座机", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100);
		
		//其他联系方式

		addColumn(datagrid, "important", "客户等级", ControlTypes.ENUM_BOX, 100);
		
//		最近任务来源
//		最近任务跟进人
//		最近任务费用部门
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 20);

		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);{
			item.setTooltip("输入客户ID、客户名称、联系方式等");
			item.setWidth(350);
		}
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
