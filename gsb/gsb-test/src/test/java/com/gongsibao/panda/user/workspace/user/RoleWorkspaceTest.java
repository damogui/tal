package com.gongsibao.panda.user.workspace.user;

import org.junit.Before;
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

import com.gongsibao.entity.uc.Role;

public class RoleWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		entity = Role.class;
		urlList = "/user/center/role/list";
		urlForm = "/user/center/role/form";
		listPartName = formPartName = "角色列表";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode =  "User_Center_" + entity.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("角色列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "flag", "标志", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "enabled", "状态", ControlTypes.ENUM_BOX, 80);{
			
			column.setStyler("return row.enabled==false?'color:red;':'color:#5FB878;';");
			column.setFormatter(" return value==false?'停用':'启用';");
		}
		addColumn(datagrid, "sort", "排序", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "description", "描述信息", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 80);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "flag", "标志", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "enabled", "启用", null, ControlTypes.SWITCH_BUTTON, false);
		addFormField(form, "sort", "排序", null, ControlTypes.NUMBER_BOX, true);
		addFormField(form, "description", "描述信息", null, ControlTypes.TEXTAREA, false);
		addFormField(form, "remark", "说明", null, ControlTypes.TEXTAREA, false);
		
		return form;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "flag", "标志", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "姓名", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "enabled", "启用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
