package com.gongsibao.panda.crm.workspace.sys;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
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
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.SalesmaProductDetailPart;
import com.gongsibao.crm.web.SysSalesmanListPart;
import com.gongsibao.crm.web.SysSalesmanTreePart;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Salesman;

//员工管理
public class SysSalesmanWorkspaceTest extends WorkspaceCreationBase {

	protected String treeResourceNodeCode = "GSB_CRM_SYS_DEPARTMENT";

	public void setup() {
		super.setup();
		urlList = "/crm/sys/salesman/list";
		urlForm = "/crm/sys/salesman/form";
		entity = Salesman.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_SYS_SALESMAN";
		formOpenMode = OpenMode.WINDOW;
		listPartImportJs = "/gsb/crm/sys/js/sys-salesman-list-part.js";
		listPartJsController = SysSalesmanListPart.class.getName();
		listPartServiceController = SysSalesmanListPart.class.getName();

		formJsImport = "/gsb/crm/js/salesman-form.part.js|/gsb/gsb.customer.controls.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("员工管理");
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setAutoQuery(false);
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "employee.name", "姓名", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "employee.loginName", "帐号", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "disabled", "状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("return controllerdepartments.disabledFormatter(value,row,index);");
		}
		column = addColumn(datagrid, "receiving", "接单", ControlTypes.TEXT_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("return controllerdepartments.receivingFormatter(value,row,index);");
		}
		addColumn(datagrid, "department.name", "部门", ControlTypes.TEXT_BOX, 120);
		column = addColumn(datagrid, "employee.loginNum", "登录次数", ControlTypes.TEXT_BOX, 100);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130);

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();

		addQueryItem(queryProject, "employee.name", "姓名", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "employee.mobile", "手机号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "employee.loginName", "帐号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);

		return queryProject;
	}

	// 表单填充字段
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(3);

		String groupName = "基本信息";
		addFormField(form, "name", "姓名", groupName, ControlTypes.TEXT_BOX, true);
		addFormField(form, "mobile", "手机号", groupName, ControlTypes.TEXT_BOX, true);
		PFormField formField = addFormField(form, "loginName", "帐号", null, ControlTypes.TEXT_BOX, false);
		{

			formField.setReadonly(true);
			formField.setTooltip("自动生成");
		}
		addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "entryDate", "入职日期", groupName, ControlTypes.DATE_BOX, false);
		addFormField(form, "quitDate", "离职日期", groupName, ControlTypes.DATE_BOX, false);
		addFormField(form, "disabled", "停用", groupName, ControlTypes.SWITCH_BUTTON, false,true);

		groupName = "属性设置";
		addFormField(form, "receiving", "接单", groupName, ControlTypes.SWITCH_BUTTON, false,true);
		
		//这里还有很多属性，
		
		return form;
	}

	@Test
	@Override
	public void run() {

		this.createTreeWorkspace();
		this.createFormWorkspace();
	}

	// 配置树状结构
	public void createTreeWorkspace() {

		ResourceNode node = resourceService.byCode(treeResourceNodeCode);// 树状的节点
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("员工管理");
			workspace.setUrl(urlList);
		}

		PPart part = new PPart();// 创建部分
		{
			part.toNew();
			part.setCode("GsbCrmSysDepartmentTree");// 树名
			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
			part.setDockStyle(DockType.LEFT);
			part.setStyle("width:250px;");
			part.setResourceNode(node);
			// 进行扩展
			// part.setToolbar(listToolbarPath);

			part.setJsController(SysSalesmanTreePart.class.getName());
			part.setServiceController(SysSalesmanTreePart.class.getName());
			part.setImports("/gsb/crm/sys/js/sys-salesman-tree-part.js");
		}
		workspace.getParts().add(part);

		ResourceNode node2 = resourceService.byCode(resourceNodeCode);
		PDatagrid datagrid = this.createDatagrid(node2);
		part = new PPart();
		{
			part.toNew();
			part.setCode("departments");
			part.setParentCode("GsbCrmSysDepartmentTree");// 点击父之后，刷新自己
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("departmentId");// 点击父之后，刷新自己所传的参数
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

	// 创建明细里面的弹窗操作
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		// 添加角色
		addRolesDetailPart(workspace);

		// 服务范围
		addScopesDetailPart(workspace);
	}

	// 添加角色
	private void addRolesDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_CRM_SYS_SALESMAN_ADDROLE");
		PDatagrid datagrid = new PDatagrid(node, "角色信息");
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;

		addColumn(datagrid, "role.name", "角色", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false, null, null, null);
		column = addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130, false, null, null, null);

		PForm form = new PForm();// 添加表单
		{
			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(2);
			form.setName("角色信息");

			PFormField field = null;
			field = addFormFieldRefrence(form, "role.name", "角色", null, Role.class.getSimpleName(), true, false);
			{

			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("角色信息");
			part.setCode("roles");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("roles");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(400);
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

	private void addScopesDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_CRM_SYS_SALESMAN_Product");
		PDatagrid datagrid = new PDatagrid(node, "添加服务范围");
		{
			PDatagridColumn column = null;
			addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 200, false);
			addColumn(datagrid, "province.name", "省", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "city.name", "市", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "county.name", "区", ControlTypes.TEXT_BOX, 150, false);

		}

		PForm form = new PForm();// 添加表单
		{
			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(1);
			form.setName("添加服务范围");

			PFormField formField = null;
			formField = addFormField(form, "productCategory1.name", "一级分类", null, ControlTypes.CUSTOM, true, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setTroikaTrigger("controllerproducts.productCategory1Select(record);");
				formField.setRefFilter("type=201 and pid=0");
			}

			formField = addFormField(form, "productCategory2.name", "二级分类", null, ControlTypes.CUSTOM, true, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setTroikaTrigger("controllerproducts.productCategory2Select(record);");
				formField.setRefFilter("type=201 and pid<>0");
			}

			formField = addFormFieldRefrence(form, "product.name", "产品", null, "CRM_" + Product.class.getSimpleName(), true, false);
			{
				formField.setWidth(200);
				formField.setRefFilter("enabled=1");
				formField.setTroikaTrigger("controllerproducts.productChange(newValue,oldValue);");
			}
			formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'city_name'");
			}
			formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'county_name'");
			}
			formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
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
			part.setCode("products");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("products");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setJsController(SalesmaProductDetailPart.class.getName());
			part.setServiceController(SalesmaProductDetailPart.class.getName());
			part.setWindowWidth(400);
			part.setWindowHeight(450);
			part.setForm(form);
		}
		workspace.getParts().add(part);

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
