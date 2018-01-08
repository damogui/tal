package com.gongsibao.panda.trade.workspace.audit;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.AuditLog;

/**   
 * @ClassName:  RefundAuditWorkspaceTest   
 * @Description:TODO 退单审核
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:08:12   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class RefundAuditWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = AuditLog.class;// 实体
		urlList = "/trade/audit/refund/list";// 列表的url
		urlForm = "/trade/audit/refund/form";// 弹出框的url
		listPartName = formPartName = "退单审核列表";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Audit_Refund_Aduit";// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowHeight = 400;
		openWindowWidth = 800;
		listFilter = "type_id=1046 AND add_user_id='{userId}' ";

		/*
		 * listPartServiceController = OrderOperationController.class.getName();
		 * listPartJsController = OrderOperationController.class.getName();
		 * listPartImportJs = "/gsb/trade/js/orderoperation.list.part.js";
		 * listToolbarPath = "/trade/manage/order/operation/toolbar";
		 */
	}

	/*
	 * @Test public void createToolbar() { ResourceNode node =
	 * this.getResourceNode(); PToolbar toolbar = new PToolbar(); {
	 * toolbar.toNew(); toolbar.setBasePath("panda/datagrid/edit");
	 * toolbar.setPath(listToolbarPath); toolbar.setName("订单操作工具栏");
	 * toolbar.setResourceNode(node); } addToolbarItem(toolbar, "disabled",
	 * "批量转移", "fa fa-edit", "batchTransferWeb()", null, 5);
	 * toolbarService.save(toolbar); }
	 */

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("退单审核列表");
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "审核id", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
		}
		// 注释掉的列暂时出不来
		addColumn(datagrid, "fefund.soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "fefund.soOrder.accountType", "新老客户签单", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "fefund.soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 300);		
		addColumn(datagrid, "fefund.soOrder.payStatus.name", "订单支付状态", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "fefund.soOrder.processStatus.name", "订单办理状态", ControlTypes.TEXT_BOX, 80);		
		addColumn(datagrid, "fefund.soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "fefund.soOrder.payablePrice", "订单金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "fefund.soOrder.paidPrice", "付款金额", ControlTypes.DECIMAL_BOX, 80);		
		addColumn(datagrid, "fefund.amount", "退款金额", ControlTypes.DECIMAL_BOX, 80);		
		addColumn(datagrid, "fefund.soOrder.isInstallment", "是否分期", ControlTypes.BOOLCOMBO_BOX, 80);
		/*
		 * addColumn(datagrid, "", "业务员", ControlTypes.TEXT_BOX, 100);
		 * addColumn(datagrid, "", "申请人", ControlTypes.TEXT_BOX, 100);
		 * addColumn(datagrid, "", "申请时间", ControlTypes.DATETIME_BOX, 100);
		 */
		addColumn(datagrid, "fefund.soOrder.accountName", "下单人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "fefund.soOrder.accountMobile", "下单人电话", ControlTypes.TEXT_BOX, 100);
		// addColumn(datagrid, "", "关联企业", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "fefund.soOrder.addTime", "下单时间", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "statusId", "审核状态", ControlTypes.ENUM_BOX, 80);

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "contract.soOrder.no", "订单编号", ControlTypes.TEXT_BOX);
		/*
		 * addQueryItem(queryProject, "channelOrderNo", "渠道订单编号",
		 * ControlTypes.TEXT_BOX); addQueryItem(queryProject, "operator", "业务员",
		 * ControlTypes.TEXT_BOX); addQueryItem(queryProject, "oldOperator",
		 * "原业务员", ControlTypes.TEXT_BOX); addQueryItem(queryProject,
		 * "customerName", "下单人", ControlTypes.TEXT_BOX);
		 * addQueryItem(queryProject, "accountMobile", "下单人手机号",
		 * ControlTypes.TEXT_BOX);
		 */
		return queryProject;
	}

	// 默认的表单配置信息
	/*
	 * protected PForm createForm(ResourceNode node) { PForm form =
	 * super.createForm(node); form.setColumnCount(3); PFormField field = null;
	 * addFormField(form, "orderNo", "订单号", ControlTypes.TEXT_BOX, true, false);
	 * return form; }
	 */

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
