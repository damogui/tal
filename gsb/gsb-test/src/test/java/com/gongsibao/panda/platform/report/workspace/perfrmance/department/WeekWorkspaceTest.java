package com.gongsibao.panda.platform.report.workspace.perfrmance.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.PerformanceStatistics;

public class WeekWorkspaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = PerformanceStatistics.class;
		urlList = "/report/perfrmance/department/week";
		listPartName = formPartName = "部门月统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Department_Perfrmance_Week";
		listFilter = "dateType=4 and organizationType=2";
		listPartType = PartType.TREEGRID_PART.getId();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setTreeField("department_shortName");
		datagrid.setAutoQuery(false);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "department.shortName", "部门", ControlTypes.TEXT_BOX, 300);
		column = addColumn(datagrid, "year", "年份", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "week", "周", ControlTypes.TEXT_BOX, 80);{
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

		column = addColumn(datagrid, "isLeaf", "isLeaf", ControlTypes.TEXT_BOX, 100);
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
		PQueryItem item = null;
		item = addQueryItem(queryProject, "year", "年", ControlTypes.YEAR_BOX);
		{
			item.setRequired(true);
		}
		item = addQueryItem(queryProject, "week", "周", ControlTypes.TEXT_BOX);
		{
			item.setRequired(true);
		}
		addRefrenceQueryItem(queryProject, "department.shortName", "部门", "Gsb_Organization");
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
