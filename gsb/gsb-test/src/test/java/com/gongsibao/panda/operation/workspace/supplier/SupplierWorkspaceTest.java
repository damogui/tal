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
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.FunctionModule;
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
		formJsImport = "/gsb/supplier/js/supplier-form-part.js|/gsb/gsb.customer.controls.js";
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
			part.setParentCode("SupplierCategoryTree");// 点击父之后，刷新自己
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("categoryId");// 点击父之后，刷新自己所传的参数
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
		addFormField(form, "mobilePhone", "手机号", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "address", "地址", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "customerMaxCount", "客户池数量", null, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "messageNotifiedType", "消息通知类型 ", null, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "noFollowDays", "未跟进天数(释放)", null, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "departLevel", "部门级次", null, ControlTypes.NUMBER_BOX, false, true);
		addFormField(form, "departmentCount", "部门数量", null, ControlTypes.NUMBER_BOX, false, true);
		addFormField(form, "salesmanCount", "员工数量", null, ControlTypes.NUMBER_BOX, false, true);
		addFormField(form, "pushReport", "是否推送报表", null, ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "autoAssign", "是否推自动分配", null, ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "autoRelease", "是否推自动释放", null, ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "enableDepart", "是否启用部门", null, ControlTypes.SWITCH_BUTTON, false, false);
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {
		
		// 功能明细
		createFunctionModulePart(workspace);
		
		// 服务产品
		createIntenProductPart(workspace);
		
		//服务地区
		createIntenDistrictPart(workspace);
	}

	// 功能明细
	private void createFunctionModulePart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_Function_Module");
		PDatagrid datagrid = new PDatagrid(node, "开通功能");
		{
			addColumn(datagrid, "functionModule.name", "名称", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("开通功能");

			PFormField formField = null;
			addFormFieldRefrence(form, "functionModule.name", "功能", null, FunctionModule.class.getSimpleName(), false, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("开通功能");
			part.setCode("modules");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("modules");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(550);
			part.setWindowHeight(200);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setDockStyle(DockType.TOP);
		}
	}

	// 服务范围
	private void createIntenProductPart(PWorkspace workspace) {
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_Service_Product");
		PDatagrid datagrid = new PDatagrid(node, "服务产品");
		{
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 300);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("服务产品");

			PFormField formField = null;
			formField = addFormField(form, "product.name", "服务产品", null, ControlTypes.CUSTOM, true, false);
			{
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setRefFilter("type=201");
			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("服务产品");
			part.setCode("serviceProducts");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("serviceProducts");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);

	}
	
	private void createIntenDistrictPart(PWorkspace workspace) {
	
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_Service_District");
		PDatagrid datagrid = new PDatagrid(node, "服务地区");
		{
			addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("服务地区");

			PFormField formField = null;
			formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, true, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'city_name'");
				formField.setWidth(300);
			}
			formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'county_name'");
				formField.setWidth(300);
			}
			// 自定义控件(公用的，里面有一些逻辑)
			formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:3");
				formField.setWidth(300);
			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("服务地区");
			part.setCode("serviceDistricts");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("serviceDistricts");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);

	}
	
	
	@Override
	protected void doOperation() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
