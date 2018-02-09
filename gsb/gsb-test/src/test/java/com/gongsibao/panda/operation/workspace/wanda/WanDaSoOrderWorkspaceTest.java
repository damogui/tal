package com.gongsibao.panda.operation.workspace.wanda;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.SoOrderListPart;

public class WanDaSoOrderWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = SoOrder.class;
		urlList = "/operation/wanda/order/list";
		//配置表单路径
		urlForm = "/operation/wanda/order/from";
		listPartName = formPartName = "订单查询列表";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_WANDA_" + SoOrder.class.getSimpleName();
		//formOpenMode = OpenMode.WINDOW;
		// 扩展
		listPartServiceController = SoOrderListPart.class.getName();
		listPartJsController = SoOrderListPart.class.getName();
		listPartImportJs = "/gsb/trade/js/soOrder.list.part.js";
	}

	@Test
	public void detailPart() {
		ResourceNode node = this.resourceService.byCode("GSB_WANDA_" + OrderProdTrace.class.getSimpleName());
		//OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("/operation/wanda/prod/detail");
			toolbar.setName("产品进度");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-mail-reply-all");
			item.setName("产品进度");
			item.setCommand(null);
			//item.setOperationType(ot1);
			item.setSeq(1);
			item.setCommand("{controllersoOrderList}.add();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("开通会员工具栏操作");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "add", "新增任务", "fa fa-plus", "add()", null, 5);
		addToolbarItem(toolbar, "batchTransfer", "任务转移", "fa fa-share-square-o", "batchTransfer();", null,6);
		return toolbar;
	}
	//创建选项卡
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		addIntenProductPart(workspace);
	}	
	//选项卡加载项
	private void addIntenProductPart(PWorkspace workspace) {
		//需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode("GSB_WANDA_" + OrderProd.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "产品明细");
		{
			addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 300);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("产品明细");

			PFormField formField = null;
			formField = addFormField(form, "productName", "产品名称", ControlTypes.TEXT_BOX, false, false);
		
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("产品明细");
			part.setCode("products");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("products");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("/operation/wanda/prod/detail");
			//part.setJsController("com.gongsibao.trade.web.SoOrderListPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		/*
		 * { datagrid.toNew(); datagrid.setResourceNode(node);
		 * datagrid.setName("订单查询列表"); datagrid.setShowCheckbox(false);
		 * 
		 * }
		 */
		datagrid.setShowCheckbox(false);
		datagrid.setToolbar("panda/datagrid/row/edit");
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		PDatagridColumn column = null;
		addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "accountName", "客户名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "prodName", "服务名称", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		addColumn(datagrid, "payTime", "订单时间", ControlTypes.DATE_BOX, 130);
		column = addColumn(datagrid, "processStatus.name", "订单状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		return datagrid;
	}
	//创建表单。须配置urlForm路径
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(2);
		}
		PFormField formField = null;
		String groupName = null;
		
		formField = addFormField(form, "no", "名称", groupName, ControlTypes.TEXT_BOX, true, false);
		formField = addFormField(form, "accountName", "客户名称", groupName, ControlTypes.TEXT_BOX, true, false);
		formField = addFormField(form, "prodName", "服务名称", groupName, ControlTypes.TEXT_BOX, true, false);
		formField = addFormField(form, "payTime", "订单时间", groupName, ControlTypes.DATE_BOX, true, false);
		return form;
	}	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "accountName", "客户名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "prodName", "服务名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "payTime", "订单时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}