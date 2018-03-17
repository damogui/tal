package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanAllOrderFormPart;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;

/*全部订单*/
public class SalesmanOrderAllWorkspaceTest extends WorkspaceCreationBase {
	private String listrowToolbarPath = "/crm/roworderall/toolbar";

	@Override
	@Before
	public void setup() {
		super.setup();
		entity = SoOrder.class;
		urlList = "/crm/order/salesman/all/list";
		listPartName = "全部订单";
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		listToolbarPath = "crm/order/all/list";
		
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/salesman-order-all-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		ss.add("/gsb/panda-extend/gsb.pubcontrol.js");
		listPartImportJs = StringManager.join("|", ss);

		listFilter = "owner_id = '{userId}'";
		listPartJsController = SalesmanAllOrderListPart.class.getName();
		listPartServiceController = SalesmanAllOrderListPart.class.getName();

		
		ss = new ArrayList<String>();
		ss.add("/package/easyui/datagrid-cellediting.js");
		ss.add("/package/easyui/datagrid-groupview.js");
		ss.add("/gsb/platform/trade/js/salesman-order-all-form.part.js");
		// ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = SalesmanAllOrderFormPart.class.getName();
		formServiceController = SalesmanAllOrderFormPart.class.getName();

	}

	@Test
	public void createListToolbar() {

		String listToolbarPath = "crm/order/all/list";
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.view);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("所有订单操作");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addOrderReceived");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("创建订单业绩");
			item.setOperationType(ot1);
			item.setSeq(2);
			item.setCommand("{controller}.addOrderReceived();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addReceived");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("创建回款业绩");
			item.setSeq(3);
			item.setCommand("{controller}.addReceived();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addCarryover");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("创建结转");
			item.setSeq(4);
			item.setCommand("{controller}.addCarryover();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addContract");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("创建合同");
			item.setSeq(5);
			item.setCommand("{controller}.addContract();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addRefund");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("申请退款");
			item.setSeq(6);
			item.setCommand("{controller}.addRefund();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addStaging");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("申请分期");
			item.setSeq(7);
			item.setCommand("{controller}.addStaging();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addInvoice");
			item.setIcon(PToolbarHelper.iconCheck);
			item.setName("申请发票");
			item.setSeq(8);
			item.setCommand("{controller}.addInvoice();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("batchOrderTran");
			item.setIcon(PToolbarHelper.iconTran);
			item.setName("批量转移");
			item.setSeq(9);
			item.setCommand("{controller}.batchOrderTran();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	@Test
	public void createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listrowToolbarPath);
			toolbar.setName("转移");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("orderTran");
			item.setName("转移");
			item.setSeq(2);
			item.setCommand("{controller}.orderTran();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{

			String toolbarPath = listrowToolbarPath;
			datagrid.setName("全部订单");
			datagrid.setToolbar(toolbarPath);
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		column = addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "payStatus", "支付状态", ControlTypes.ENUM_BOX, 60);
		addColumn(datagrid, "companyIntention.companyName", "签单企业", ControlTypes.TEXT_BOX, 250);
		column = addColumn(datagrid, "totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 80);
		{
			column.setAlign(DatagridAlign.RIGHT);
		}
		column = addColumn(datagrid, "payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 80);
		{
			column.setAlign(DatagridAlign.RIGHT);
		}
		column = addColumn(datagrid, "paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 80);
		{
			column.setAlign(DatagridAlign.RIGHT);
		}
		addColumn(datagrid, "payTime", "回款日期", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "refundStatus", "退款状态", ControlTypes.ENUM_BOX, 60);
		addColumn(datagrid, "isInstallment", "分期付款", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "customerName", "客户", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "accountMobile", "客户手机号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "sourceType", "下单方式", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "platformSource", "订单来源", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "createTime", "下单时间", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "accountType", "新老客户", ControlTypes.ENUM_BOX, 80);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		queryProject.setColumnCount(3);

		item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
		{
			item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、签单企业");
			item.setWidth(350);
		}
		addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "platformSource", "订单来源", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "payStatus", "付款状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "sourceType", "下单方式", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "owner.name", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "isInstallment", "分期付款", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "createTime", "订单创建日期", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "payTime", "回款日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
