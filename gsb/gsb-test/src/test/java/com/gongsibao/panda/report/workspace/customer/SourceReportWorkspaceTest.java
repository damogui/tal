package com.gongsibao.panda.report.workspace.customer;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.report.web.sourcReport.CustomerSourceReportPart;

public class SourceReportWorkspaceTest extends WorkspaceCreationBase{


	@Override
	@Before
	public void setup() {
		entity = BaseCustomerReportEntity.class;
		urlList = "/report/customer/source";
		listPartName = formPartName = "来源统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Report_Customer_Source";
		
		listPartType = PartType.TREEGRID_REPORT_PART.getId();
		listPartServiceController = CustomerSourceReportPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "lineName", "线上线下", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "name", "客户来源", ControlTypes.TEXT_BOX, 100);
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
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
