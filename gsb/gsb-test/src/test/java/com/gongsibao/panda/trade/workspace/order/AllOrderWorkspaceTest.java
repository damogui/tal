package com.gongsibao.panda.trade.workspace.order;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.SoOrder;

/**   
 * @ClassName:  AllOrderWorkspaceTest   
 * @Description:TODO 全部订单
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:11:52   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class AllOrderWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {
		entity = SoOrder.class;
		urlList = "/trade/manage/order/all/list";
		listPartName = formPartName = "订单查询列表";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Manage_All_Order";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "type", "订单类型", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "accountName", "客户名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "accountMobile", "手机号", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "payStatusId", "支付状态", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "prodName", "服务名称", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		column = addColumn(datagrid, "processStatusId", "执行进度", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "refundStatusId", "退款状态", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "totalPrice", "总金额", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "payablePrice", "未支付金额", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "paidPrice", "已支付金额", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "sourceTypeId", "来源类型", ControlTypes.NUMBER_BOX, 90);
		addColumn(datagrid, "payTime", "支付时间", ControlTypes.DATE_BOX, 130);
		column = addColumn(datagrid, "processStatus.name", "订单状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		return datagrid;
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
