package org.netsharp.meta.basebiz.organization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.controller.EmployeeListPart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.dic.PostType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.EmployeeCity;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;

public class EmployeeWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/system/employee/list";
		urlForm = "/system/employee/form";
		resourceNodeCode = Employee.class.getSimpleName();
		entity = Employee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();

		this.listPartImportJs = "/panda-bizbase/organization/org-employee-list-part.js";
		this.listToolbarPath = "ykx/emplyee/list/toolbar";
		this.listPartServiceController = EmployeeListPart.class.getName();
		this.listPartJsController = EmployeeListPart.class.getName();

		this.formJsController = "org.netsharp.panda.commerce.EmployeeFormPart";
		this.formJsImport = "/panda-bizbase/organization/org-employee-form-part.js";

		// formOpenMode = OpenMode.WINDOW;
		// openWindowWidth = 800;
		// openWindowHeight = 600;
	}

	@Test
	public void createToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("员工列表工具栏");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "disabled", "停用", "fa-stop-circle-o", "disabled(true)", null, 5);
		addToolbarItem(toolbar, "disabled", "启用", "fa-check", "disabled(false)", null, 6);
		addToolbarItem(toolbar, "resetPassword", "重置密码", "fa-history", "resetpwd()", null, 7);
		addToolbarItem(toolbar, "permissions", "查看权限", "fa-list-alt", "permissions()", null, 8);
		toolbarService.save(toolbar);
	}

	// protected void createRowStyler(PDatagrid datagrid) {
	//
	// this.addRowStyler(datagrid, "row.disabled", "color:red;");
	// }

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);

		addColumn(datagrid, "name", "姓名", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "loginName", "登录名", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "education", "学历", ControlTypes.ENUM_BOX, 80);

		column = addColumn(datagrid, "disabled", "停用", ControlTypes.BOOLCOMBO_BOX, 60, true);
		column.setStyler("return row.disabled==true?'color:red;':'color:#5FB878;';");
		column.setFormatter(" return value==true?'是':'否';");

		addColumn(datagrid, "entryDate", "入职日期", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "mobile", "手机", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "email", "邮件", ControlTypes.TEXT_BOX, 150);
		column = addColumn(datagrid, "nativePlace", "籍贯", ControlTypes.TEXT_BOX, 80);
		{

			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "department.name", "部门", ControlTypes.TEXT_BOX, 100);
		{

			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "post.name", "岗位", ControlTypes.TEXT_BOX, 100);
		{

			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "quitDate", "离职日期", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 130);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "姓名", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobile", "手机", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "disabled", "停用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		addOrganizationsDetailPart(workspace);
		addCitysDetailPart(workspace);
		PPart part = workspace.getParts().get(0);
		{
			part.setCode("employee");
			part.setStyle("height:340px;");
			part.setDockStyle(DockType.TOP);
		}
	}

	private void addOrganizationsDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(OrganizationEmployee.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "岗位信息");
		// datagrid.setIconCls("fa fa-vcard-o");
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;
		column = addColumn(datagrid, "postType", "岗位类型", ControlTypes.ENUM_BOX, 100);
		{

			String formatter = EnumUtil.getColumnFormatter(PostType.class);
			column.setFormatter(formatter);
		}
		addColumn(datagrid, "organization.pathName", "岗位", ControlTypes.REFERENCE_BOX, 400);
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
			part.setName("岗位信息");
			part.setCode("organizations");
			part.setParentCode("employee");
			part.setRelationRole("organizations");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);

			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	private void addCitysDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(EmployeeCity.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "影响城市");
		// datagrid.setIconCls("fa fa-vcard-o");
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;
		column = addColumn(datagrid, "fProvince.name", "省份", ControlTypes.REFERENCE_BOX, 150);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "fCity.name", "城市", ControlTypes.DATETIME_BOX, 150);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "fCounty.name", "区/县", ControlTypes.DATETIME_BOX, 150);
		{

			column.setAlign(DatagridAlign.CENTER);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("影响城市");
			part.setCode("citys");
			part.setParentCode("employee");
			part.setRelationRole("citys");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);

			PForm form = this.createCityDetailGridForm(node);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	protected PForm createCityDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("城市");
		String groupName = null;
		PFormField formField = addFormField(form, "fProvince.name", "省份", groupName, ControlTypes.CUSTOMER, false, false);
		{
			formField.setCustomerControlType("com.gongsibao.controls.CityComboBox");
			formField.setDataOptions("level:1,changeCtrlId:'fCity_name'");
		}

		formField = addFormField(form, "fCity.name", "城市", groupName, ControlTypes.CUSTOMER, false, false);
		{

			formField.setCustomerControlType("com.gongsibao.controls.CityComboBox");
			formField.setDataOptions("level:2,changeCtrlId:'fCounty_name'");
		}

		formField = addFormField(form, "fCounty.name", "区/县", groupName, ControlTypes.CUSTOMER, false, false);
		{

			formField.setCustomerControlType("com.gongsibao.controls.CityComboBox");
			formField.setDataOptions("level:3");
		}
		return form;
	}

	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("岗位");
		addFormField(form, "postType", "类型", null, ControlTypes.ENUM_BOX, true, false);
		PFormField field = addFormField(form, "organization.pathName", "岗位", null, ControlTypes.REFERENCE_BOX, true, false);
		{

			PReference reference = referenceService.byCode("Organization-Post");
			field.setReference(reference);
		}
		return form;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		String groupName = null;
		addFormField(form, "name", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
		PFormField field = addFormField(form, "loginName", "登录名", groupName, ControlTypes.TEXT_BOX, true, true);
		field.setTooltip("登录名自动生成");
		field = addFormField(form, "mobile", "手机号", groupName, ControlTypes.TEXT_BOX, true, false);
		{

			field.setTroikaValidation("mobile");
		}
		field = addFormField(form, "email", "电子邮件", groupName, ControlTypes.TEXT_BOX, false, false);
		{

			field.setTroikaValidation("email");
		}
		addFormField(form, "nativePlace", "籍贯", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "education", "学历", groupName, ControlTypes.RADIO_BOX_GROUP, false, false);
		addFormField(form, "bankNo", "工资卡号", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "weixin", "微信号", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "entryDate", "入职日期", groupName, ControlTypes.DATE_BOX, false, false);
		addFormField(form, "quitDate", "离职日期", groupName, ControlTypes.DATE_BOX, false, false);
		field = addFormField(form, "disabled", "停用", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{

			// field.setDataOptions("|");
		}
		addFormField(form, "memoto", "备注", groupName, ControlTypes.TEXTAREA, false, false);

		return form;
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(entity.getSimpleName());
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.delete);
	}
}
