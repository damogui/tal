package com.gongsibao.panda.platform.product.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.dto.OrderStatisticsDTO;

public class OrderStatisticsYearWorkspaceTest extends WorkspaceCreationBase{
	
	@Before
	public void setup() {

		super.setup();
		urlList = "/prod/year/statistics";

		entity = OrderStatisticsDTO.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "GSB_Product_Manage_statisticsYear";
		formPartName = listPartName = meta.getName();
		listFilter = "type=1";

	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("订单统计(年)");
		}
		
		addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "year", "年份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "count", "销售数量", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "price", "总额", ControlTypes.TEXT_BOX, 200);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "product_name", "产品名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "add_time", "下单时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);

	}
}
