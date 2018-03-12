package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.trade.web.OrderOperationController;

public class OrderOperationWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = SoOrderDTO.class;// 实体
		urlList = "/trade/manage/order/operation/list";// 列表的url
		urlForm = "/trade/manage/order/operation/form";// 弹出框的url
		listPartName = formPartName = "订单操作列表";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Manage_Order_Operation";// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowHeight = 400;
		openWindowWidth = 800;

		listPartServiceController = OrderOperationController.class.getName();
		listPartJsController = OrderOperationController.class.getName();
		listPartImportJs = "/gsb/platform/trade/js/orderoperation.list.part.js";
		listToolbarPath = "/trade/manage/order/operation/toolbar";
	}

	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("订单操作工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "disabled", "批量转移", "fa fa-edit", "batchTransferWeb()", null, 5);
		toolbarService.save(toolbar);
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("订单操作列表");
			datagrid.setSingleSelect(false);
			datagrid.setShowCheckbox(true);
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "订单id", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
		}
		addColumn(datagrid, "orderNo", "订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "payTime", "回款日期", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "orderStatus", "订单状态", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "companyName", "关联企业", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "refundStatusId", "退单状态", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "totalPrice", "原价金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "payablePrice", "应付金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "paidPrice", "已付金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "isInstallment", "是否分期", ControlTypes.BOOLCOMBO_BOX, 100);
		addColumn(datagrid, "operationTraceInfo", "批量转移记录", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "operator", "业务员", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "oldOperator", "原业务员", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "customerName", "下单人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "platformSource", "订单来源", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "addTime", "订单创建日期", ControlTypes.DATETIME_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "orderNo", "订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "operator", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "oldOperator", "原业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "customerName", "下单人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "accountMobile", "下单人手机号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "orderStatus", "订单状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "productName", "产品名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "companyName", "关联企业", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "operationTraceInfo", "批量转移记录", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "platformSource", "订单来源", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "isInstallment", "是否分期", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "addTime", "订单创建日期", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "payTime", "回款日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {
		PForm form = super.createForm(node);
		form.setColumnCount(3);
		PFormField field = null;

		addFormField(form, "orderNo", "订单号", ControlTypes.TEXT_BOX, true, false);

		return form;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		/*
		 * operationService.addOperation(node,OperationTypes.add);
		 * operationService.addOperation(node,OperationTypes.update);
		 * operationService.addOperation(node,OperationTypes.delete);
		 */
	}
}