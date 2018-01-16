package com.gongsibao.panda.crm.workspace.sys;


import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.SupplierDepartment;

//部门管理工作空间
public class SysDepartmentWorkspaceTest   extends WorkspaceCreationBase{
	public void setup() {
		super.setup();
		urlList = "/crm/sys/department/list";
		urlForm = "/crm/sys/department/form";
		entity = SupplierDepartment.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_SYS_DEPARTMENT";
		formOpenMode = OpenMode.WINDOW;
	
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
//			datagrid.setToolbar("panda/datagrid/row/edit");//这个东西
			datagrid.setName("部门管理");
		}
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
//		column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "code", "部门编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "部门名称", ControlTypes.TEXT_BOX, 80);
	
		
column = addColumn(datagrid, "disabled", "停用", ControlTypes.BOOLCOMBO_BOX, 80);{
			
			column.setStyler("return row.disabled==true?'color:red;':'color:#5FB878;';");
			column.setFormatter(" return value==false?'使用':'停用';");
		}
		addColumn(datagrid, "path_code", "路径编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "path_name", "路径名称", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 80);
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
		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "disabled", "是否停用", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		return queryProject;
	}

 //表单填充字段
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		//addFormFieldRefrence类中类字段
		addFormField(form, "name", "部门名称", null, ControlTypes.TEXT_BOX, false);
		addFormField(form, "disabled", "是否停用", null, ControlTypes.SWITCH_BUTTON, true);

		return form;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}


	
	
	


}
