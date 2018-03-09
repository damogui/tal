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

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.report.ComprehenReportPart;
import com.gongsibao.entity.crm.report.ComprehenReportEntity;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;


/**
 * 综合统计
 * @author Administrator
 *
 */
public class ComprehenStatisticalWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = ComprehenReportEntity.class;
		urlList = "/operation/statistical/comprehen/list";
		listPartName = formPartName = "综合统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_STATISTICAL_COMPREHEN";
		listPartType = PartType.TREEGRID_PART.getId();
		listPartServiceController = ComprehenReportPart.class.getName();
		listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setTreeField("departmentName");
		datagrid.setLazy(true);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "departmentName", "部门", ControlTypes.TEXT_BOX, 300, true);
		column = addColumn(datagrid, "customerCount", "全部客户数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "taskCount", "全部任务数", ControlTypes.NUMBER_BOX, 100);
		
		/*column = addColumn(datagrid, "selfCustomerCount", "自拓客户数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "selfTaskCount", "自拓任务数", ControlTypes.NUMBER_BOX, 100);*/
		column = addColumn(datagrid, "allocationTaskCount", "分配任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "intoTaskCount", "转入任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "rollOutTaskCount", "转出任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "returnTaskCount", "退回任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "withdrawTaskCount", "收回任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "followTaskCount", "跟进任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "unSignTaskCount", "无法签单任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "checkAbnormalTaskCount", "抽查异常任务数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "signingAmount", "预估签单额", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "returnedAmount", "预估回款额", ControlTypes.NUMBER_BOX, 100);
		
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
		item = addQueryItem(queryProject, "source.name", "任务来源", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=411");
		}
		return queryProject;
	}
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
