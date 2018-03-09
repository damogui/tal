package com.gongsibao.panda.operation.workspace.supplier;

import org.junit.Before;
import org.junit.Test;
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
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.SupplierCategory;
import com.gongsibao.supplier.web.SupplierCategoryTreegridPart;

public class SupplierCategoryWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/operation/supplier/category/list";
		urlForm = "/operation/supplier/category/form";

		entity = SupplierCategory.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Operation_Supplier_Category";
		listPartType = PartType.TREEGRID_PART.getId();
		listPartImportJs="/gsb/supplier/sys/js/organization/supplier-category-list-part.js|/gsb/gsb.custom.query.controls.js";
		listPartJsController = SupplierCategoryTreegridPart.class.getName();
		listPartServiceController = SupplierCategoryTreegridPart.class.getName();
		listToolbarPath = "/operation/supplier/category/toolbar";
		
		formOpenMode = OpenMode.WINDOW;
	}
	
	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("同步路径");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "pathCode", "同步路径", "fa-recycle", "pathCode()", null, 5);
		addToolbarItem(toolbar, "reload", "刷新", "fa-refresh", "reload()", null, 5);
		toolbarService.save(toolbar);
	}


	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		
		PDatagridColumn column = null;

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 120);{
			column.setAlign(DatagridAlign.CENTER);
		}
		
		addColumn(datagrid, "pathName", "路径", ControlTypes.TEXT_BOX, 400);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
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

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);

		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(1);
		}

		PFormField formField = null;
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);{
			
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
