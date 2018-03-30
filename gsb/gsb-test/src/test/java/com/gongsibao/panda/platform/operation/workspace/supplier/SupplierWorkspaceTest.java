package com.gongsibao.panda.platform.operation.workspace.supplier;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
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
import com.gongsibao.crm.web.TaskProductDetailPart;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.FunctionModule;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierCategory;
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
		listPartImportJs = "/gsb/supplier/sys/organization/js/supplier-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listPartJsController = SupplierListPart.class.getName();
		listPartServiceController = SupplierListPart.class.getName();
		formJsImport = "/gsb/supplier/sys/organization/js/supplier-form-part.js|/gsb/panda-extend/gsb.customer.controls.js";
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
			part.setName("服务商分类");
			part.setHeaderVisible(true);
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
		addColumn(datagrid, "category.name", "分类", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 80, false);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 80, false);
		column = addColumn(datagrid, "openTime", "开户时间", ControlTypes.DATETIME_BOX, 130, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, false);
		column = addColumn(datagrid, "contact", "联系人", ControlTypes.TEXT_BOX, 80, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "mobilePhone", "手机号", ControlTypes.TEXT_BOX, 100, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "bankName", "银行名", ControlTypes.TEXT_BOX, 150, false);
		addColumn(datagrid, "bankNum", "银行卡号", ControlTypes.TEXT_BOX, 150, false);
		addColumn(datagrid, "address", "地址", ControlTypes.TEXT_BOX, 200, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(2);
			form.setLabelWidth(150);
		}
		PFormField field = null;

		String groupName = "基本信息";
		field = addFormFieldRefrence(form, "category.name", "分类", groupName, SupplierCategory.class.getSimpleName(), true, false);{
			
			field.setControlType(ControlTypes.COMBOTREE_BOX);
			field.setWidth(300);
		}
		
		field = addFormField(form, "name", "服务商名称", groupName, ControlTypes.TEXT_BOX, true);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "contact", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "mobilePhone", "手机号", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			field.setTroikaValidation("['mobile']");
			field.setWidth(300);
		}
		field = addFormField(form, "address", "地址", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "sex", "性别", groupName, ControlTypes.RADIO_BOX_GROUP, true, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "telePhone", "座机", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "postCode", "邮编", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setTroikaValidation("['ZIP']");
			field.setWidth(300);
		}
		field = addFormField(form, "fax", "传真", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "qq", "QQ", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
			field.setTroikaValidation("['qq']");
		}
		field = addFormField(form, "weixin", "微信", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
			field.setTroikaValidation("['email']");
		}
		field = addFormField(form, "headPortrait", "头像", groupName, ControlTypes.OSS_UPLOAD, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "province.name", "所在省份", groupName, ControlTypes.CUSTOM, false, false);
		{
			field.setWidth(300);
			field.setCustomControlType(CityComboBox.class.getName());
			field.setDataOptions("level:1,changeCtrlId:'city_name'");
		}
		field = addFormField(form, "city.name", "所在城市", groupName, ControlTypes.CUSTOM, false, false);
		{
			field.setWidth(300);
			field.setCustomControlType(CityComboBox.class.getName());
			field.setDataOptions("level:2,changeCtrlId:'county_name'");
		}
		field = addFormField(form, "county.name", "所在区/县", groupName, ControlTypes.CUSTOM, false, false);
		{
			field.setWidth(300);
			field.setCustomControlType(CityComboBox.class.getName());
			field.setDataOptions("level:3");
		}
		field = addFormField(form, "address", "详细地址", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}

		groupName = "认证信息";
		field = addFormField(form, "startBusiness", "从业开始时间", groupName, ControlTypes.DATE_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "identityCard", "身份证号", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setTroikaValidation("['idcard']");
			field.setWidth(300);
		}
		field = addFormField(form, "identityCardPhotoFont", "身份证照片正面", groupName, ControlTypes.OSS_UPLOAD, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "identityCardPhotoReverse", "身份证照片反面", groupName, ControlTypes.OSS_UPLOAD, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "startBusiness", "执业资质照片", groupName, ControlTypes.OSS_UPLOAD, false, false);
		{
			field.setWidth(300);
		}

		groupName = "服务信息";
		field = addFormField(form, "responseTime", "响应时间", groupName, ControlTypes.ENUM_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "serviceDeclaration", "服务宣言", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "serviceIntro", "服务简介", groupName, ControlTypes.TEXTAREA, false, false);
		{
			field.setWidth(300);
			field.setHeight(80);
			field.setTooltip("参考内容：<br>1、个人介绍，如毕业院校、所在单位、从业经历等；<br>2、个人擅长领域；<br>3、做过的相关案例、项目经验等");
		}
		field = addFormField(form, "consigneeAddress", "收件地址", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}

		groupName = "财务信息";
		
		field = addFormField(form, "bankName", "开户银行", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "bankAccountType", "帐户类型", groupName, ControlTypes.RADIO_BOX_GROUP, false, false);
		{
			field.setWidth(300);
		}		
		field = addFormField(form, "bankAccountName", "帐号名称", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "bankNum", "银行卡号", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setWidth(300);
			field.setTroikaValidation("['bankNum']");
		}

		groupName = "系统设置";
		field = addFormField(form, "status", "状态", groupName, ControlTypes.ENUM_BOX, true, true);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "type", "类型", groupName, ControlTypes.RADIO_BOX_GROUP, true, false);
		{
			field.setWidth(300);
		}		
		field = addFormField(form, "customerMaxCount", "客户池数量", groupName, ControlTypes.NUMBER_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "notifiedType", "消息通知类型 ", groupName, ControlTypes.RADIO_BOX_GROUP, false, true);
		{
			field.setWidth(300);
		}		
		field = addFormField(form, "noFollowDays", "未跟进天数-释放", groupName, ControlTypes.NUMBER_BOX, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "customerType", "客户类别 ", groupName, ControlTypes.RADIO_BOX_GROUP, false, false);
		{
			field.setWidth(300);
		}
		field = addFormField(form, "pushReport", "是否推送报表", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{

		}
		field = addFormField(form, "autoAssign", "是否推自动分配", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{

		}
		field = addFormField(form, "autoRelease", "是否推自动释放", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{

		}
		field = addFormField(form, "enableDepart", "是否启用部门", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{

		}
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		// 功能明细
		createFunctionModulePart(workspace);

		// 服务产品
		createIntenProductPart(workspace);
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
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}

	// 服务范围
	private void createIntenProductPart(PWorkspace workspace) {
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_Service_Product");
		PDatagrid datagrid = new PDatagrid(node, "服务范围");
		{
			addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 200, false);
			addColumn(datagrid, "nProvince.name", "省", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "nCity.name", "市", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "nCounty.name", "区", ControlTypes.TEXT_BOX, 150, false);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("服务范围");

			PFormField formField = null;
			formField = addFormField(form, "productCategory1.name", "一级分类", null, ControlTypes.CUSTOM, true, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setTroikaTrigger("controllerserviceProducts.productCategory1Select(newValue,oldValue);");
				formField.setRefFilter("type=201 and pid=0");
			}

			formField = addFormField(form, "productCategory2.name", "二级分类", null, ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setTroikaTrigger("controllerserviceProducts.productCategory2Select(newValue,oldValue);");
				formField.setRefFilter("type=201 and pid<>0");
			}

			formField = addFormFieldRefrence(form, "product.name", "产品", null, "CRM_" + Product.class.getSimpleName(), false, false);
			{
				formField.setWidth(200);
				formField.setRefFilter("enabled=1");
			}
			formField = addFormField(form, "nProvince.name", "省份", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'nCity_name'");
			}
			formField = addFormField(form, "nCity.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'nCounty_name'");
			}
			formField = addFormField(form, "nCounty.name", "区/县", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:3");
			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("服务范围");
			part.setCode("serviceProducts");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("serviceProducts");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setJsController("com.gongsibao.crm.web.SupplierProductDetailPart");
			part.setServiceController(TaskProductDetailPart.class.getName());
			part.setWindowWidth(400);
			part.setWindowHeight(550);
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
