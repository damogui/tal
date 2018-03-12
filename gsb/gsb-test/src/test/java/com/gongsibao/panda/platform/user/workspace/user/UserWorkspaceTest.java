package com.gongsibao.panda.platform.user.workspace.user;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
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
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.uc.User;
import com.gongsibao.entity.uc.UserBusiness;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.entity.uc.UserRoleMap;
import com.gongsibao.uc.web.UserFormPart;

public class UserWorkspaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/user/center/user/list";
		urlForm = "/user/center/user/from";
		resourceNodeCode = "User_Center_"+User.class.getSimpleName();
		entity = User.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		
//		this.listPartImportJs = "/panda-bizbase/organization/org-employee-list-part.js";
//		this.listToolbarPath = "ykx/emplyee/list/toolbar";
//		this.listPartServiceController = EmployeeListPart.class.getName();
//		this.listPartJsController = EmployeeListPart.class.getName();

		formServiceController = UserFormPart.class.getName();
		formJsController = UserFormPart.class.getName();
		formJsImport = "/gsb/platform/user/js/user.form.part.js|/gsb/panda-extend/gsb.customer.controls.js";

		// formOpenMode = OpenMode.WINDOW;
		// openWindowWidth = 800;
		// openWindowHeight = 600;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		addColumn(datagrid, "name", "姓名", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "mobilePhone", "手机", ControlTypes.TEXT_BOX, 100);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "isInner", "内部", ControlTypes.BOOLCOMBO_BOX, 80);
		column = addColumn(datagrid, "enabled", "状态", ControlTypes.BOOLCOMBO_BOX, 80);{
			
			column.setStyler("return row.enabled==false?'color:red;':'color:#5FB878;';");
			column.setFormatter(" return value==false?'离职':'在职';");
		}
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 80);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "姓名", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobilePhone", "手机", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "isInner", "内部", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "enabled", "停用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		addOrganizationsDetailPart(workspace);
		
		addUserBusinessDetailPart(workspace);
		
		addUserRoleDetailPart(workspace);
		
		PPart part = workspace.getParts().get(0);
		{
			part.setCode("user");
			part.setStyle("height:340px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}
	
	private void addUserBusinessDetailPart(PWorkspace workspace) {
		
		ResourceNode node = this.resourceService.byCode("User_Center_" +UserBusiness.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, null);
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;

		addColumn(datagrid, "business.name", "名称", ControlTypes.REFERENCE_BOX, 300);		
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false, null, null, null);

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("归属事业部");
			part.setCode("business");
			part.setParentCode("user");
			part.setRelationRole("business");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);

			
			PForm form = new PForm();{
				
				form.setResourceNode(node);
				form.toNew();
				form.setColumnCount(1);
				form.setName("归属事业部");
				PFormField field = addFormField(form, "business.name", "名称", null, ControlTypes.CUSTOM, true, false);
				{
			    	field.setCustomControlType(DictComboBox.class.getName());
			    	field.setRefFilter("type=108");
				}
				
			}
			
			part.setForm(form);
		}
		workspace.getParts().add(part);
		
	}
	
	private void addUserRoleDetailPart(PWorkspace workspace) {
		
		ResourceNode node = this.resourceService.byCode("User_Center_" +UserRoleMap.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, null);
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;

		addColumn(datagrid, "role.name", "角色名称", ControlTypes.REFERENCE_BOX, 150);	
		addColumn(datagrid, "canPass", "可分配", ControlTypes.BOOLCOMBO_BOX, 80);	
		
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false, null, null, null);

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("分配角色");
			part.setCode("roles");
			part.setParentCode("user");
			part.setRelationRole("roles");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);
			
			PForm form = new PForm();{
				
				form.setResourceNode(node);
				form.toNew();
				form.setColumnCount(1);
				form.setName("分配角色");
				PFormField field = addFormField(form, "role.name", "所属组织", null, ControlTypes.REFERENCE_BOX, true, false);
				{
					PReference reference = referenceService.byCode("Gsb_Role");
					field.setReference(reference);
				}
				addFormField(form, "canPass", "可分配", null, ControlTypes.SWITCH_BUTTON, false, false);
			}
			
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	private void addOrganizationsDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("User_Center_" +UserOrganizationMap.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, null);
		// datagrid.setIconCls("fa fa-vcard-o");
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;

		addColumn(datagrid, "organization.shortName", "部门", ControlTypes.REFERENCE_BOX, 300);		
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false, null, null, null);

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("所属组织");
			part.setCode("organizations");
			part.setParentCode("user");
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


	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("所属组织");
		PFormField field = addFormField(form, "organization.shortName", "所属组织", null, ControlTypes.REFERENCE_BOX, true, false);
		{
			PReference reference = referenceService.byCode("Gsb_Organization");
			field.setReference(reference);
		}
		return form;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		String groupName = null;
		PFormField field = null;
		addFormField(form, "name", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
		field = addFormField(form, "mobilePhone", "手机号", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			field.setTroikaValidation("validationMobile");
		}
		addFormField(form, "loginValid", "登录验证", groupName, ControlTypes.ENUM_BOX, false, false);

	    
	    field = addFormField(form, "abbility.name", "服务能力", groupName, ControlTypes.CUSTOM, false, false);{
	    	
	    	field.setCustomControlType(DictComboBox.class.getName());
	    	field.setRefFilter("type=107");
	    }
	    
	    field = addFormField(form, "priority.name", "分配优先级", groupName, ControlTypes.CUSTOM, false, false);{
	    	
	    	field.setCustomControlType(DictComboBox.class.getName());
	    	field.setRefFilter("type=109");
	    }
	    
	    addFormFieldRefrence(form, "office.name", "所属分公司", groupName, "Gsb_Organization", false, false);
	    
		field = addFormField(form, "email", "电子邮件", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			field.setTroikaValidation("email");
		}
		addFormField(form, "qq", "QQ", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "weixin", "微信", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "sex", "性别", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "isInner", "内部员工", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		
		field = addFormField(form, "ukeyPid", "Ukey序列号", groupName, ControlTypes.TEXT_BOX, false, false);{
			
			field.setFullColumn(true);
	    }
	    
		field = addFormField(form, "pubKey", "Ukey公钥", groupName, ControlTypes.TEXTAREA, false, false);{
			field.setHeight(100);
		}
		return form;
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
	}
}
