package com.gongsibao.panda.operation.workspace.supplier;

import com.gongsibao.entity.uc.Role;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.FunctionModule;

public class FunctionModuleWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = FunctionModule.class;
		urlList = "/operation/supplier/module/list";
		urlForm = "/operation/supplier/module/form";
		listPartName = formPartName = "功能模块";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Operation_Function_Module";
		openWindowWidth = 800;
		openWindowHeight = 600;
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("功能模块列表");
		}
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		{
			form.setColumnCount(2);
		}

		PFormField formField = null;
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		{
			formField.setHeight(100);
			formField.setFullColumn(true);
		}
		return form;
	}
	
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		addRolesDetailPart(workspace);
		
		PPart part = workspace.getParts().get(0);
		{
			part.setCode("module");
			part.setStyle("height:250px;");
			part.setDockStyle(DockType.TOP);
		}
	}
	
	private void addRolesDetailPart(PWorkspace workspace) {
		
		ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_Function_Module_Role");
		PDatagrid datagrid = new PDatagrid(node, "角色信息");
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;

		addColumn(datagrid, "role.name", "角色", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150, false, null, null, null);
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false, null, null, null);

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("角色信息");
			part.setCode("roles");
			part.setParentCode("module");
			part.setRelationRole("roles");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(450);

			PForm form = this.createRoleDetailGridForm(node);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	
	protected PForm createRoleDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("角色");

		PFormField field = null;
		field = addFormFieldRefrence(form, "role.name", "角色", null, Role.class.getSimpleName(), true, false);{
			
		}
		return form;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
