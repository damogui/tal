package com.gongsibao.panda.operation.workspace.crm.report;

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

import com.gongsibao.crm.web.report.FollowReportPart;
import com.gongsibao.entity.crm.report.FollowReportEntity;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;


/**
 * 跟进统计
 * @author Administrator
 *
 */
public class FollowStatisticalWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = FollowReportEntity.class;
		urlList = "/operation/statistical/follow/list";
		listPartName = formPartName = "跟进统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_STATISTICAL_FOLLOW";
		listPartType = PartType.TREEGRID_PART.getId();
		listPartServiceController = FollowReportPart.class.getName();
		listPartImportJs = "/gsb/gsb.custom.query.controls.js";
	}
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setTreeField("departmentName");
		datagrid.setLazy(true);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "departmentName", "部门", ControlTypes.TEXT_BOX, 300, true);
		column = addColumn(datagrid, "taskCount", "全部任务数", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "unfoolowCount", "待跟进任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "timeOutCount", "超时任务数", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "foolowCount", "跟进任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "qualityRisetaskCount", "质量上升任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "qualityDeclinetaskCount", "质量下降任务数", ControlTypes.NUMBER_BOX, 100);
		
		
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
		item = addQueryItem(queryProject, "date", "日期", ControlTypes.DATE_BOX);{
			item.setRequired(true);
		}
		addRefrenceQueryItem(queryProject, "supplier.name", "服务商", Supplier.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "department.name", "部门", SupplierDepartment.class.getSimpleName());
		
		return queryProject;
	}
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
