package com.gongsibao.panda.user.workspace.user;

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
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.uc.Organization;
import com.gongsibao.part.CustomTreePart;

public class OrganizationWorkspaceTest  extends WorkspaceCreationBase{

	String treeToolBarPath = "user/organization/tree";
	@Before
	public void setup() {

		urlList = "/user/center/organization/list";
		urlForm = "/user/center/organization/form";

		entity = Organization.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "User_Center_" + entity.getSimpleName();
		formPartName = listPartName = meta.getName();

		listPartImportJs="/gsb/platform/user/js/organization.list.part.js";
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
	}
	
	@Test
	@Override
	public void run() {

		this.createTreeWorkspace();
		this.createFormWorkspace();
	}
	
	@Test
	public void createTreeToolBarPath(){
		
		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/tree");
			toolbar.setPath(treeToolBarPath);
			toolbar.setName("组织机构");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("pathCode");
			item.setIcon("fa fa-refresh fa-fw");
			item.setName("刷新");
			item.setSeq(10000);
			item.setCommand("{controller}.pathCode();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}
	
	public void createTreeWorkspace() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("组织机构工作区");
			workspace.setUrl(urlList);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("OrganizationTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
			part.setDockStyle(DockType.LEFT);
			part.setStyle("width:280px;");
			part.setResourceNode(node);
			part.setToolbar(treeToolBarPath);
			part.setUrl(this.urlForm);
			part.setServiceController(CustomTreePart.class.getName());
			//part.setJsController(CustomTreePart.class.getName());
		}
		workspace.getParts().add(part);

		ResourceNode node2 = resourceService.byCode(resourceNodeCode);
		PDatagrid datagrid = this.createDatagrid(node2);
		part = new PPart();
		{
			part.toNew();
			part.setCode("items");
			part.setParentCode("OrganizationTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("parentId");
			part.setToolbar(this.listToolbarPath);
			part.setUrl(this.urlForm);
			part.setOpenMode(formOpenMode);
			part.setWindowWidth(openWindowWidth);
			part.setWindowHeight(openWindowHeight);
			part.setJsController("com.gongsibao.uc.web.OrganizationListPart");
//			part.setServiceController(OrganizationEmployeeListPart.class.getName());
			part.setImports(listPartImportJs);
			part.setResourceNode(node2);
		}

		workspace.getParts().add(part);

		workspaceService.save(workspace);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setShowTitle(true);
			datagrid.setName("组织列表");
		}
		
		PDatagridColumn column = null;
		
		addColumn(datagrid, "organizationType", "类型", ControlTypes.ENUM_BOX, 80);
		column = addColumn(datagrid, "city.name", "地区", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "公司名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "shortName", "组织名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "enabled", "启用", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "remark", "备注", ControlTypes.TEXT_BOX, 300);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(2);
		}
		
		PFormField field = null;

		addFormField(form, "organizationType", "类型", null, ControlTypes.ENUM_BOX, true);
		addFormField(form, "name", "公司名称", null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "shortName", "组织名称", null, ControlTypes.TEXT_BOX, true,false);
		addFormFieldRefrence(form, "leader.name", "主管人", null, "Gsb_User", false,false);

		//city
		addFormField(form, "enabled", "启用", null, ControlTypes.SWITCH_BUTTON, false,false);
		addFormField(form, "remark", "备注", null, ControlTypes.TEXTAREA, false,false);
		return form;
	}
	

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
