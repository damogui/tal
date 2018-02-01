package com.gongsibao.panda.basic.workspace.u8;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.dto.PayReceiptCheckDTO;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.u8.web.PayReceiptCheckDTOController;

public class PayReceiptCheckDTOWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = PayReceiptCheckDTO.class;// 实体
		urlList = "/basic/u8/receiptcheck/list";// 列表的url
		urlForm = "/basic/u8/receiptcheck/form";// 弹出框的url
		listPartName = formPartName = "回单核对";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + PayReceiptCheckDTO.class.getSimpleName();// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowHeight = 400;
		openWindowWidth = 800;

		listPartServiceController = PayReceiptCheckDTOController.class.getName();
		listPartJsController = PayReceiptCheckDTOController.class.getName();
		listPartImportJs = "/gsb/bd/js/u8/pay.receipt.part.js";
		listToolbarPath = "/basic/u8/receiptcheck/toolbar";
	}

	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("回单核对工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "disabled", "绑定回单编号", "fa fa-edit", "bindReceiptWeb();", null, 5);
		toolbarService.save(toolbar);
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("回单核对");
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "支付编号", ControlTypes.NUMBER_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "u8VoucherId", "u8凭证号", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "payForOrderCount", "支付订单数量", ControlTypes.ENUM_BOX, 80);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "receiptNo", "回单编号", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "orderIdStr", "订单id", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
		}
		column = addColumn(datagrid, "orderNo", "订单号", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "payablePriceStr", "订单金额", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "paidPriceStr", "订单已支付金额", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "amount", "支付金额", ControlTypes.DECIMAL_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "bookName", "付款账套", ControlTypes.TEXT_BOX, 200);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "bankName", "支付方式", ControlTypes.TEXT_BOX, 150);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "receiptStatus", "回单处理状态", ControlTypes.ENUM_BOX, 100);
		{
			column.setFormatter("return controllerpayReceiptCheckDTOList.changeReceiptStatusFormatter(value,row,index);");
		}
		column = addColumn(datagrid, "addTimeStr", "订单创建日期", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "returnTime", "回款日期", ControlTypes.DATETIME_BOX, 100);
		{
			column.setImported(true);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "orderNo", "订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "u8VoucherId", "u8凭证id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "receiptNo", "回单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "id", "支付编号", ControlTypes.NUMBER_BOX);
		addQueryItem(queryProject, "payForOrderCount", "支付订单数量", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "receiptStatus", "回单处理状态", ControlTypes.ENUM_BOX);		
		//参照
		addRefrenceQueryItem(queryProject, "book.name", "账套", SetOfBooks.class.getSimpleName());
		PQueryItem item = addRefrenceQueryItem(queryProject, "bank.name", "付款方式", U8Bank.class.getSimpleName());
		{
			item.setRefFilter("type=0");
		}
		addQueryItem(queryProject, "addTime", "订单创建日期", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "returnTime", "回款日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.exportExcel);
		/*
		 * operationService.addOperation(node,OperationTypes.add);
		 * operationService.addOperation(node,OperationTypes.update);
		 * operationService.addOperation(node,OperationTypes.delete);
		 */
	}
}
