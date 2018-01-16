package com.gongsibao.panda.crm.workspace.sys;

import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import com.gongsibao.entity.supplier.Salesman;

public class SysSalesmanWorkspaceTest  extends WorkspaceCreationBase{
	public void setup() {
		super.setup();
		urlList = "/crm/sys/salesman/list";
		entity = Salesman.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_SYS_SALESMAN";
		formOpenMode = OpenMode.WINDOW;
	
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("员工管理");
		}
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "employee_id", "部门编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "department.name", "部门id", ControlTypes.TEXT_BOX, 80);
	
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "create_time", "创建时间", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "update_time", "修改时间", ControlTypes.TEXT_BOX, 100);
		
		return datagrid;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
//		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "disabled", "是否停用", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		return queryProject;
	}
}
