package com.gongsibao.panda.platform.operation.workspace.supplier;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Role;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.supplier.SupplierCategory;
import com.gongsibao.supplier.web.SupplierCategoryTreegridPart;

public class SupplierCategoryWorkspaceTest extends WorkspaceCreationBase {

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
		listPartImportJs = "/gsb/supplier/sys/organization/js/supplier-category-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listPartJsController = SupplierCategoryTreegridPart.class.getName();
		listPartServiceController = SupplierCategoryTreegridPart.class.getName();
		listToolbarPath = "/operation/supplier/category/toolbar";

		formOpenMode = OpenMode.WINDOW;
		openWindowHeight = 600;
		openWindowWidth = 800;
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
		column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 120);
		{
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
		addRefrenceQueryItem(queryProject, "ownerMaps.owner.name", "运营专员", Employee.class.getSimpleName());
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
		formField = addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		{

			formField.setWidth(300);
		}

		formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		{

			formField.setHeight(100);
			formField.setWidth(300);
			formField.setFullColumn(false);
		}
		return form;
	}
	
    // 创建明细里面的弹窗操作
    @Override
    protected void addDetailGridPart(PWorkspace workspace) {

        // 添加角色
        addOwnerMapDetailPart(workspace);
    }
    
    // 添加角色
    private void addOwnerMapDetailPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_CategoryOwnerMap");
        PDatagrid datagrid = new PDatagrid(node, "角色信息");
        datagrid.setShowCheckbox(true);
        datagrid.setSingleSelect(false);
        datagrid.setReadOnly(true);

        PDatagridColumn column = null;

        addColumn(datagrid, "owner.name", "运营专员", ControlTypes.TEXT_BOX, 100);

        PForm form = new PForm();// 添加表单
        {
            form.setResourceNode(node);
            form.toNew();
            form.setColumnCount(2);
            form.setName("运营专员");

            PFormField field = null;
            field = addFormFieldRefrence(form, "owner.name", "运营专员", null, Employee.class.getSimpleName(), false, false);
    		{
    		}
        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("运营专员");
            part.setCode("ownerMaps");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("ownerMaps");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setWindowWidth(600);
            part.setWindowHeight(400);
            part.setForm(form);
        }
        workspace.getParts().add(part);

        part = workspace.getParts().get(0);
        {
            part.setName("基本信息");
			part.setDockStyle(DockType.TOP);
			part.setHeight(160);
        }
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
