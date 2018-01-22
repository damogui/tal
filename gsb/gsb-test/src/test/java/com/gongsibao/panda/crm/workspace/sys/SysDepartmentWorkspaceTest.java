package com.gongsibao.panda.crm.workspace.sys;

import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.SysDepartmentTreeGridPart;
import com.gongsibao.entity.supplier.SupplierDepartment;

//部门管理工作空间
public class SysDepartmentWorkspaceTest extends WorkspaceCreationBase {
	
	public void setup() {
		super.setup();
		urlList = "/crm/sys/department/list";
		urlForm = "/crm/sys/department/form";
		entity = SupplierDepartment.class;
		meta = MtableManager.getMtable(entity);
		listPartType = PartType.TREEGRID_PART.getId();
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_SYS_DEPARTMENT";
		formOpenMode = OpenMode.WINDOW;
		listPartImportJs="/gsb/crm/sys/js/sys-department-list-part.js";
		listPartJsController = SysDepartmentTreeGridPart.class.getName();
		listPartServiceController = SysDepartmentTreeGridPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("部门管理");
			datagrid.setAutoQuery(false);
		}
		PDatagridColumn column = null;

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "isLeaf", "isLeaf", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	// 表单填充字段
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "name", "部门名称", null, ControlTypes.TEXT_BOX, false);
		PFormField formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);{
			
			formField.setHeight(100);
			formField.setFullColumn(true);
		}
		return form;
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
