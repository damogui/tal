package com.gongsibao.panda.platform.report.workspace.customer;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.customer.CustomerStatusReport;
import com.gongsibao.report.web.statusReport.CustomerStatusReportPart;



public class StatusReportWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = CustomerStatusReport.class;
		urlList = "/report/customer/status";
		listPartName = formPartName = "客户状态统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Customer_Status";
		//不是树形列表不需要添加
		//listPartType = PartType.TREEGRID_PART.getId();
		listPartServiceController =CustomerStatusReportPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setLazy(true);
		datagrid.setPagination(false);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "statusName", "客户状态", ControlTypes.TEXT_BOX, 150, true);
		column = addColumn(datagrid, "newCount", "新增数量", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "newShareCount", "分享数量", ControlTypes.NUMBER_BOX, 90);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "date", "日期", ControlTypes.DATE_BOX);{
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
