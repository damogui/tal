package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.TaskAllListPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.supplier.Supplier;

public class TaskOpenSeaWorkspaceTest extends WorkspaceCreationBase {

	protected String rowToolbaPath = "/operation/task/row/toolbar";

	@Override
	@Before
	public void setup() {

		entity = NCustomerTask.class;
		urlList = "/operation/task/opensea/list";

		listPartName = formPartName = "公海";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_OpenSea";

		listFilter = "( (owner_id is null or owner_id =0) and (department_id is null or department_id = 0) and (supplier_id is null or supplier_id = 0))";
		listToolbarPath = "task/batch/allocation";
		listPartJsController = TaskAllListPart.class.getName();
		listPartServiceController = TaskAllListPart.class.getName();
		listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
	}

	public PToolbar createListToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("批量分配");
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
			item.setName("新增商机");
			item.setOperationType(ot1);
			item.setSeq(2);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("batchAllocation");
			item.setIcon("fa fa-check");
			item.setName("批量分配");
			item.setSeq(3);
			item.setCommand("{controller}.batchAllocation();");
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
			toolbar.setName("分配");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("allocation");
			item.setName("分配");
			item.setSeq(1000);
			item.setCommand("{controller}.allocation();");
			toolbar.getItems().add(item);
		}
		
		return toolbar;
	}
	
	
	@Test
	public void saveRowToolbar() {
		
		PToolbar toolbar = createRowToolbar();
		if(toolbar != null){

			toolbarService.save(toolbar);
		}
	}

	@Test
	public void saveListToolbar() {

		PToolbar toolbar = createListToolbar();
		if(toolbar != null){

			toolbarService.save(toolbar);
		}
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setToolbar(rowToolbaPath);

		PDatagridColumn column = null;

		addColumn(datagrid, "ownerId", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "id", "商机ID", ControlTypes.TEXT_BOX, 60, false);
		addColumn(datagrid, "name", "商机名称", ControlTypes.TEXT_BOX, 250, false);
		addColumn(datagrid, "customerId", "客户ID", ControlTypes.TEXT_BOX, 60, false);
		addColumn(datagrid, "customer.realName", "客户名称", ControlTypes.TEXT_BOX, 100, false);

		// addColumn(datagrid, "customer.realName", "公司名称",
		// ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "customer.isMember", "是否会员", ControlTypes.TEXT_BOX, 100, false);{
			
			StringBuilder builder = new StringBuilder();
			builder.append("if(value===true){return '是   ';}");
			builder.append("else{");
			builder.append("if(row.customer_mobile){");
			builder.append("var ctrl = workspace.parts.byIndex(0).key;");
			builder.append("return '否<a title=\\'开通会员\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.customerId+',true));>");
			builder.append("<i style=\\'font-size: 12px;color:red;\\' class=\\'fa fa-user-plus\\'></i><a>");
			builder.append("<a title=\\'静默开通\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.customerId+',false);>");
			builder.append("<i style=\\'font-size: 12px;\\' class=\\'fa fa-user-plus\\'></i><a>';");
			builder.append("}else{return '否';}");
			builder.append("}");
			column.setFormatter(builder.toString());
			//column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "customer.mobile", "手机号", ControlTypes.TEXT_BOX, 100, false);{
			
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		addColumn(datagrid, "customer.telephone", "座机", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.qq", "QQ", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.weixin", "微信", ControlTypes.TEXT_BOX, 100, false);

		// addColumn(datagrid, "customer.realName", "其他联系方式",
		// ControlTypes.TEXT_BOX, 100, true);

		addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "quality.name", "客户质量", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);

		// addColumn(datagrid, "customer.realName", "意向产品",
		// ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "source.name", "商机来源", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 300, false);
		addColumn(datagrid, "lastFollowTime", "最后跟进时间", ControlTypes.DATETIME_BOX, 130, false);
		addColumn(datagrid, "lastFoolowUser.name", "原业务员", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false);

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
		{
			item.setTooltip("输入商机ID、客户ID、商机名称、客户名称、联系方式等");
			item.setWidth(350);
		}
		item = addQueryItem(queryProject, "source.name", "商机来源", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=411");
		}
		
		addQueryItem(queryProject, "costed", "市场费用投放", ControlTypes.BOOLCOMBO_BOX);
		
		addRefrenceQueryItem(queryProject, "supplier.name", "服务商", Supplier.class.getSimpleName());
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		addRefrenceQueryItem(queryProject, "quality.name", "客户质量", NCustomerTaskQuality.class.getSimpleName());

		//意向产品
		addQueryItem(queryProject, "foolowStatus", "商机状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}

	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
