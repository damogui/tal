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

import com.gongsibao.entity.trade.OrderProd;

/**   
 * @ClassName:  OrderPoolWorkspaceTest   
 * @Description:TODO 订单池
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:11:17   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class OrderPoolWorkspaceTest  extends WorkspaceCreationBase{

	@Before
	public void setup() {
		entity = OrderProd.class;
		urlList = "/trade/manage/order/pool/list";
		listPartName = formPartName = "订单池";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Manage_Order_Pool";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "soOrder.account.name", "客户创建人", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "id", "明细编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "soOrder.processStatus.name", "订单状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "cityIdDict.name", "产品地区", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.companyIntention.name", "明细订单公司", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "companyIntention.name", "订单关联公司", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.platformSourceDict.name", "订单来源", ControlTypes.NUMBER_BOX, 90);		
		column = addColumn(datagrid, "soOrder.addUser.name", "下单人", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.accountMobile", "下单人电话", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.prodName", "服务名称", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		column = addColumn(datagrid, "isRefund", "是否退单", ControlTypes.NUMBER_BOX, 90);{
			column.setFormatter("return value==0?'否':'是'");
		}
		column = addColumn(datagrid, "soOrder.createTime", "下单时间", ControlTypes.DATE_BOX, 90);
		//通过left多个表关联,目前随便添加的
		column = addColumn(datagrid, "soOrder.accountName", "业务员", ControlTypes.NUMBER_BOX, 90);
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
