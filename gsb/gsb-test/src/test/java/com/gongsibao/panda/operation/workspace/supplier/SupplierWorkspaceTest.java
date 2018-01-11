package com.gongsibao.panda.operation.workspace.supplier;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.supplier.web.SupplierFormPart;
import com.gongsibao.supplier.web.SupplierListPart;

/**
 * @ClassName: SupplierWorkspaceTest
 * @Description:TODO 供应商列表
 * @author: 韩伟
 * @date: 2018年1月10日 下午3:36:04
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class SupplierWorkspaceTest extends WorkspaceCreationBase {


	@Before
	public void setup() {

		urlList = "/operation/supplier/list";
		urlForm = "/operation/supplier/form";

		entity = Supplier.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "GSB_Operation_Supplier";
		formPartName = listPartName = meta.getName();

		listPartImportJs = "/gsb/supplier/js/supplier-list-part.js";
		listPartJsController = SupplierListPart.class.getName();
		listPartServiceController = SupplierListPart.class.getName();

		formJsImport = "/gsb/supplier/js/supplier-form-part.js";
		formJsController = SupplierFormPart.class.getName();
		formServiceController = SupplierFormPart.class.getName();

		listToolbarPath = "operation/supplier/list/edit";
	}

	@Test
	@Override
	public void run() {
		this.createTreeWorkspace();
		this.createFormWorkspace();
	}

	@Test
	public void createListRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("服务商列表");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "setDepartment", "部门设置", "fa-share-alt", "setDepartment()", null, 5);
		addToolbarItem(toolbar, "setSalesman", "员工设置", "fa-user-circle", "setSalesman()", null, 6);
		addToolbarItem(toolbar, "openAccount", "开户", "fa-check", "openAccount()", null, 7);
		addToolbarItem(toolbar, "closeAccount", "销户", "fa-close", "closeAccount()", null, 8);
		toolbarService.save(toolbar);
	}

	public void createTreeWorkspace() {

		ResourceNode node = resourceService.byCode("GSB_Operation_Supplier_Category");
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("服务商分类");
			workspace.setUrl(urlList);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("SupplierCategoryTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
			part.setDockStyle(DockType.LEFT);
			part.setStyle("width:250px;");
			part.setResourceNode(node);
		}
		workspace.getParts().add(part);

		ResourceNode node2 = resourceService.byCode(resourceNodeCode);
		PDatagrid datagrid = this.createDatagrid(node2);
		part = new PPart();
		{
			part.toNew();
			part.setCode("suppliers");
			part.setParentCode("SupplierCategoryTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("categoryId");
			part.setResourceNode(node2);
			part.setUrl(urlForm);
			part.setToolbar(listToolbarPath);
			part.setJsController(listPartJsController);
			part.setServiceController(listPartServiceController);
			part.setImports(listPartImportJs);
		}

		workspace.getParts().add(part);

		workspaceService.save(workspace);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("服务商列表");
			datagrid.setToolbar("panda/datagrid/row/edit");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 80, true);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 80, false);
		addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 80, false);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, false);
		addColumn(datagrid, "address", "地址", ControlTypes.TEXT_BOX, 200, false);
		addColumn(datagrid, "openTime", "开户时间", ControlTypes.DATETIME_BOX, 130, false);
		addColumn(datagrid, "departmentCount", "部门数量", ControlTypes.DECIMAL_BOX, 80, false);
		addColumn(datagrid, "salesmanCount", "员工数量", ControlTypes.DECIMAL_BOX, 80, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(3);
		}

		PFormField field = null;
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "status", "状态", null, ControlTypes.ENUM_BOX, true, true);
		addFormField(form, "type", "类型", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "address", "地址", null, ControlTypes.TEXT_BOX, false, false);
		return form;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
