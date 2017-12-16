package com.gongsibao.panda.report.workspace.perfrmance.salesman;

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

import com.gongsibao.entity.report.PerformanceStatistics;

public class MonthWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = PerformanceStatistics.class;
		urlList = "/report/perfrmance/salesman/month";
		listPartName = formPartName = "业务员月统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Perfrmance_Salesman_Month";
		listFilter = "dateType=3 and organizationType=1";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setTreeField("salesman_name");
		PDatagridColumn column = null;

		column = addColumn(datagrid, "salesman.name", "业务员", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "year", "年份", ControlTypes.TEXT_BOX, 100, true);{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "month", "月份", ControlTypes.TEXT_BOX, 100, true);{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "receivableAmount", "应收金额", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "paidAmount", "实收金额", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "refundAmount", "退款金额", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "netReceivables", "净应收", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "netPaidAmount", "净实收", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "productCount", "销售量", ControlTypes.NUMBER_BOX, 80);
		column = addColumn(datagrid, "orderCount", "订单量", ControlTypes.NUMBER_BOX, 80);
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
			column.setSystem(true);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		
		PQueryItem item = addQueryItem(queryProject, "department.shortName", "部门", ControlTypes.TEXT_BOX);
	    item = addQueryItem(queryProject, "year", "年", ControlTypes.YEAR_BOX);
		{
			item.setInterzone(true);
			item.setShortcut(true);
		}
		item = addQueryItem(queryProject, "month", "日期", ControlTypes.MONTH_BOX);
		{
			item.setInterzone(true);
			item.setShortcut(true);
		}
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
