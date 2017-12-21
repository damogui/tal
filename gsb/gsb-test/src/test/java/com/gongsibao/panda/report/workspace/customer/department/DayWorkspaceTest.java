package com.gongsibao.panda.report.workspace.customer.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.customer.CustomerDayGrowView;

public class DayWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {
		entity =CustomerDayGrowView.class;
		urlList = "/report/grow/customer/day";
		listPartName = formPartName = "日增长量统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Customer_Grow_Day";
		//listFilter = "dateType=5 and organizationType=1";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "days", "日期", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "newCustomer", "新增客户", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "newShareCustomer", "新增被共享", ControlTypes.NUMBER_BOX, 90);		
		
		return datagrid;
	}

	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
