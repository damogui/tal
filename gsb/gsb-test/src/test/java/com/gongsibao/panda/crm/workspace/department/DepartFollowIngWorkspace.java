package com.gongsibao.panda.crm.workspace.department;

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

import com.gongsibao.entity.crm.NCustomerTask;

public class DepartFollowIngWorkspace extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = NCustomerTask.class;
		urlList = "/crm/department/5/list";

		listPartName = formPartName = "跟进中";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_DEPARTMENT_FOLLOWING";
		listFilter = "foolowStatus = 3 and creator_id = '{userId}'";
		
		formJsImport = "/gsb/crm/js/crm.all.task.part.js";
	}
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "customer.realName", "客户", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "department.name", "分配服务商部门", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
//		column = addColumn(datagrid, "old", "是否老客户", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		return datagrid;
	}
	//配置查询条件
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "customer.realName", "客户", ControlTypes.TEXT_BOX);{
			item.setRequired(true);
		}
		return queryProject;
	}
	
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}		
}
