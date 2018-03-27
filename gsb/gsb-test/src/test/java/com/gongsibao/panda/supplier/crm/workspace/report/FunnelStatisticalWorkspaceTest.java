package com.gongsibao.panda.supplier.crm.workspace.report;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.report.FunelReportPart;
import com.gongsibao.entity.crm.report.FunnelReportEntity;


/**
 * 漏斗统计
 * @author Administrator
 *
 */
public class FunnelStatisticalWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = FunnelReportEntity.class;
		urlList = "/crm/statistical/customer/funnel/list";
		listPartName = formPartName = "漏斗统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_STATISTICAL_Funnel";
		listPartType = PartType.TREEGRID_PART.getId();
		listPartServiceController = FunelReportPart.class.getName();
		listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
		//统计级别""-平台；1-服务商；
		listFilter ="1";
	}
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setTreeField("departmentName");
		datagrid.setLazy(true);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "departmentName", "部门", ControlTypes.TEXT_BOX, 300, true);
		column = addColumn(datagrid, "taskCount", "全部商机数", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "XCount", "X类", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "SCount", "S类", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "A0Count", "A0", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "A1Count", "A1", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "A2Count", "A2", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "A3Count", "A3", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "A4Count", "A4", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "B1Count", "B1", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "B2Count", "B2", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "C1Count", "C1", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "C2Count", "C2", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "C3Count", "C3", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "C4Count", "C4", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "D1Count", "D1", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "D2Count", "D2", ControlTypes.NUMBER_BOX, 100);
		
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
		addRefrenceQueryItem(queryProject, "owner.name", "业务员", Employee.class.getSimpleName());
		item = addQueryItem(queryProject, "source.name", "商机来源", ControlTypes.CUSTOM);{
			
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
