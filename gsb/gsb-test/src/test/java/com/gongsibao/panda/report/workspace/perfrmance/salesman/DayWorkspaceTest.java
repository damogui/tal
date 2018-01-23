package com.gongsibao.panda.report.workspace.perfrmance.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.report.web.PerformanceStatisticsController;

public class DayWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {
		entity = PerformanceStatistics.class;
		urlList = "/report/perfrmance/salesman/day";
		listPartName = formPartName = "业务员日统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Salesman_Perfrmance_Day";
		listFilter = "dateType=5 and organizationType=1";
		//工具条添加任务
		listPartServiceController = PerformanceStatisticsController.class.getName();
		listPartJsController = PerformanceStatisticsController.class.getName();
		listPartImportJs = "/gsb/performance/js/report.part.js";
		listToolbarPath = "/bd/crm/performance/report/toolbar";
	}

	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("业绩统计工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "disabled", "生成业绩", "fa-check-circle-o", "generaResultsReports()", null, 5);
		toolbarService.save(toolbar);
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "salesman.name", "业务员", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "date", "日期", ControlTypes.DATE_BOX, 100);
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
		PQueryItem item = null;
		item = addQueryItem(queryProject, "date", "日期", ControlTypes.DATE_BOX);
		{
			item.setRequired(true);
		}
		item = addRefrenceQueryItem(queryProject, "department.shortName", "部门", "Gsb_Organization");{
			item.setRequired(false);
		}
		addRefrenceQueryItem(queryProject, "salesman.name", "业务员", "Gsb_User");
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
