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
		listPartName = formPartName = "全部订单";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Manage_All_Order";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "channelOrderNo", "渠道订单号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "payStatus.name", "支付状态", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "processStatus.name", "执行进度", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "refundStatus.name", "退款状态", ControlTypes.NUMBER_BOX, 90);		
		column = addColumn(datagrid, "totalPrice", "总金额", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "payablePrice", "未支付金额", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "paidPrice", "已支付金额", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "isInstallment", "分期付款", ControlTypes.NUMBER_BOX, 90);{
			column.setFormatter("return value==0?'否':'是'");
		}
		addColumn(datagrid, "accountName", "客户名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "accountMobile", "手机号", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "platformSourceDict.name", "订单来源", ControlTypes.NUMBER_BOX, 90);
		addColumn(datagrid, "payTime", "支付时间", ControlTypes.DATE_BOX, 130);
		addColumn(datagrid, "addTime", "创建时间", ControlTypes.DATE_BOX, 130);
		column = addColumn(datagrid, "type", "订单类型", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "prodName", "服务名称", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		column = addColumn(datagrid, "sourceType.name", "来源类型", ControlTypes.NUMBER_BOX, 90);
		//订单状态目前是取字典，但实际情况是通过几个字典计算出来暂时不处理。
		column = addColumn(datagrid, "processStatus.name", "订单状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		//通过left多个表关联
		column = addColumn(datagrid, "", "业务员", ControlTypes.NUMBER_BOX, 90);
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
