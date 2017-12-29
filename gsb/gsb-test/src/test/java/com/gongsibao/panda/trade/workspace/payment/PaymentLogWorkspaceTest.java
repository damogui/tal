package com.gongsibao.panda.trade.workspace.payment;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.PropertyQueryDictComboBox;
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
		listPartImportJs = "/gsb/gsb.customer.controls.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "soOrder.no", "产品名称", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "pay.amount", "金额", ControlTypes.DECIMAL_FEN_BOX, 90,true);
		column = addColumn(datagrid, "pay.offlinePayerName", "付款方名称", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "pay.offlineBankNo", "付款银行帐号", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "pay.offlineRemark", "付款说明", ControlTypes.TEXT_BOX, 90);

		column = addColumn(datagrid, "pay.onlineTradeNo", "线上付款银行名称", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "pay.confirmTime", "确认支付时间", ControlTypes.DATETIME_BOX, 130);
		column = addColumn(datagrid, "pay.createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		column = addColumn(datagrid, "pay.onlineTradeNo", "交易流水号", ControlTypes.TEXT_BOX, 100);

		column = addColumn(datagrid, "pay.payWayType.name", "付款方式", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "pay.successStatus.name", "支付状态", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "pay.offlineAddUser.name", "付款提交人", ControlTypes.TEXT_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "pay.offlineAuditStatus.name", "审核状态	", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "soOrder.accountName", "会员名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "soOrder.accountMobile", "会员手机号", ControlTypes.TEXT_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "no", "序号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "soOrder.accountName", "下单人名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "soOrder.accountMobile", "下单人手机号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "pay.onlineTradeNo", "线上付款银行名称", ControlTypes.TEXT_BOX);
		PQueryItem item = addQueryItem(queryProject, "pay.successStatusId", "支付状态", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=312");
		}
		item = addQueryItem(queryProject, "pay.offlineAuditStatusId", "付款审核状态", ControlTypes.CUSTOM);{
		
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=105");
		}
		item = addQueryItem(queryProject, "pay.payWayTypeId", "付款方式", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=310");
		}
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
