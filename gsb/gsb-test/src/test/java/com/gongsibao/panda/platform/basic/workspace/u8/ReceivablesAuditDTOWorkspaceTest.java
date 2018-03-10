package com.gongsibao.panda.platform.basic.workspace.u8;

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

import com.gongsibao.entity.trade.dto.ReceivablesAuditDTO;
import com.gongsibao.u8.web.ReceivablesAuditDTOController;

public class ReceivablesAuditDTOWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = ReceivablesAuditDTO.class;// 实体
		urlList = "/basic/u8/receivablesaudit/list";// 列表的url
		urlForm = "/basic/u8/receivablesaudit/form";// 弹出框的url
		listPartName = formPartName = "收款审核列表";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + ReceivablesAuditDTO.class.getSimpleName();// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowHeight = 400;
		openWindowWidth = 800;

		// 单据的才有审核和弃审
		listToolbarPath = "/basic/u8/receivablesaudit/toolbar";
		listPartServiceController = ReceivablesAuditDTOController.class.getName();
		listPartJsController = ReceivablesAuditDTOController.class.getName();
		listPartImportJs = "/gsb/platform/basic/js/u8/receivablesaudit.part.js";
		// formJsImport = "/gsb/panda-extend/gsb.customer.controls.js";
	}

	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("收款审核工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "disabled", "审核", "fa fa-check", "receivablesauditWeb()", null, 5);
		toolbarService.save(toolbar);
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("收款审核列表");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "id", "申请编号", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "orderNos", "关联订单号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "amount", "总付款金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "bookName", "付款账套", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "bankName", "付款方式", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "offlinePayerName", "付款账户名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "offlineBankNo", "付款账号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "applyUserName", "申请人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "auditStatusName", "审核状态", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "auditStatusId", "审核状态id", ControlTypes.NUMBER_BOX, 100);
		{
			column.setVisible(false);
		}
		addColumn(datagrid, "addTime", "申请日期", ControlTypes.DATETIME_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem queryItem = null;
		addQueryItem(queryProject, "id", "申请编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "orderNos", "关联订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "applyUserName", "申请人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "addTime", "申请日期", ControlTypes.DATE_BOX);
		/*
		 * queryItem = addQueryItem(queryProject, "auditStatusName", "原线下付款方式",
		 * ControlTypes.CUSTOMER); {
		 * queryItem.setCustomerControlType(DictComboBox.class.getName());
		 * queryItem.setRefFilter("type=105"); }
		 */
		return queryProject;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		// operationService.addOperation(node, OperationTypes.audit);
		/*
		 * operationService.addOperation(node,OperationTypes.add);
		 * operationService.addOperation(node,OperationTypes.update);
		 * operationService.addOperation(node,OperationTypes.delete);
		 */
	}
}
