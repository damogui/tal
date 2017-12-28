package com.gongsibao.panda.trade.workspace.payment;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.panda.trade.ResourceTest;

public class PaymentLogWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {
		entity = OrderPayMap.class;
		urlList = "/trade/payment/log/list";
		listPartName = formPartName = "支付记录";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = ResourceTest.resourcePrefix + "_Payment_Payment_Log";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "soOrder.no", "产品名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "pay.amount", "金额", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "pay.offlinePayerName", "付款方名称", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "pay.offlineBankNo", "付款银行帐号", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "pay.offlineRemark", "付款说明", ControlTypes.TEXT_BOX, 90);
		
		column = addColumn(datagrid, "pay.onlineTradeNo", "线上付款银行名称", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "pay.confirmTime", "确认支付时间", ControlTypes.TEXT_BOX, 80);
		column = addColumn(datagrid, "pay.createTime", "创建时间", ControlTypes.DATETIME_BOX, 80);
		column = addColumn(datagrid, "pay.onlineTradeNo", "交易流水号", ControlTypes.TEXT_BOX, 100);
		
		column = addColumn(datagrid, "pay.payWayTypeId", "付款方式", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "pay.successStatusId", "支付状态", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "pay.offlineAddUserId", "付款提交人", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "pay.offlineAuditStatusId", "审核状态	", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "soOrder.account.name", "会员名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "soOrder.account.mobilePhone", "会员手机号", ControlTypes.TEXT_BOX, 100);
		return datagrid;
	}

	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		
//		序号:  
//		订单编号:
//		下单人名称:
//		下单人手机号:
//		线上付款银行名称:
//		支付状态:
//		付款审核状态:
//		付款方式:
			
//		item = addQueryItem(queryProject, "date", "日期", ControlTypes.DATE_BOX);
//		{
//			item.setRequired(true);
//		}
//		item = addRefrenceQueryItem(queryProject, "department.shortName", "部门", "Gsb_Organization");
//		{
//			item.setRequired(true);
//		}
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
