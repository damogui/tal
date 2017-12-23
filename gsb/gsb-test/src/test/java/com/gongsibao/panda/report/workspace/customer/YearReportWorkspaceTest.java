package com.gongsibao.panda.report.workspace.customer;

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

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.report.web.dataReport.CustomerYearReportPart;

public class YearReportWorkspaceTest extends WorkspaceCreationBase{


	@Override
	@Before
	public void setup() {
		entity = BaseCustomerReportEntity.class;
		urlList = "/report/customer/year";
		listPartName = formPartName = "年统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Customer_Year";
		listPartType = PartType.TREEGRID_PART.getId();
		listPartServiceController = CustomerYearReportPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setTreeField("orgName");
		PDatagridColumn column = null;

		column = addColumn(datagrid, "orgName", "部门", ControlTypes.TEXT_BOX, 300, true);
		column = addColumn(datagrid, "year", "年份", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "newCount", "新增数量", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "newShareCount", "分享数量", ControlTypes.NUMBER_BOX, 90);
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
		item = addQueryItem(queryProject, "year", "年份", ControlTypes.YEAR_BOX);{
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
