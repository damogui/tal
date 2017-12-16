package com.gongsibao.panda.franchisee.workspace.my;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
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
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.entity.franchisee.dic.ExpectedSign;
import com.gongsibao.entity.franchisee.dic.IntentionDegree;
import com.gongsibao.entity.franchisee.dic.TrackProgress;
import com.gongsibao.franchisee.web.FranchiseeFormPart;
import com.gongsibao.franchisee.web.TrackDetailPart;

public class MyFranchiseeWorkspaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/bd/franchisee/my/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "供应商信息";
		resourceNodeCode = "BD_MY_MY";
		listFilter = "ownerId='{userId}'";
		formJsImport = "/gsb/bd/js/franchisee.form.part.js";
		formServiceController = FranchiseeFormPart.class.getName();
		formJsController = FranchiseeFormPart.class.getName();
		this.formToolbarPath = "bd/franchisee/form";
	}
	
	@Test
	public void run() {
		fromToolbar();
		createListWorkspace();
		createFormWorkspace();
	}
	
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		OperationType otAdd = operationTypeService.byCode(OperationTypes.add);
		OperationType otUpdate = operationTypeService.byCode(OperationTypes.update);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			//toolbar.setBasePath("panda/form/edit");
			toolbar.setPath(this.formToolbarPath);
			toolbar.setName("供应商表单");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("follow");
			item.setIcon("fa fa-mail-reply-all");
			item.setName("跟进");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(5000);
			item.setCommand("{controller}.follow();");
			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setSeq(600);
			item.setCommand("{controller}.add();");
			item.setOperationType(otAdd);
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("save");
			item.setIcon("fa fa-save");
			item.setName("保存");
			item.setCommand(null);
			item.setSeq(700);
			item.setCommand("{controller}.save();");

			item.setOperationType(otAdd);
			item.setOperationType2(otUpdate);
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);{
			//column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "name", "公司名称", ControlTypes.TEXT_BOX, 200, true);{
			//column.setGroupName(groupName);
		}
		String groupName = "基本信息";
		column = addColumn(datagrid, "employeeCount", "人数", ControlTypes.NUMBER_BOX, 80);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "legalPerson", "法人", ControlTypes.TEXT_BOX, 80);{
			column.setGroupName(groupName);
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "annualIncome", "年收入(万)", ControlTypes.DECIMAL_BOX, 80);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "province.name", "省", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "city.name", "市", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "county.name", "区", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setGroupName(groupName);
		}
		
		column = addColumn(datagrid, "registerAddress", "注册地址", ControlTypes.TEXT_BOX, 200);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "workdAddress", "办公地址", ControlTypes.TEXT_BOX, 200);{
			column.setGroupName(groupName);
		}
		
		groupName = "主联系人信息";
		column = addColumn(datagrid, "linkmanName", "联系人", ControlTypes.TEXT_BOX, 80);{
			column.setGroupName(groupName);
		}

		column = addColumn(datagrid, "mobile", "手机", ControlTypes.TEXT_BOX, 100);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		
		column = addColumn(datagrid, "post", "职务", ControlTypes.TEXT_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 80);{
			column.setGroupName(groupName);
		}

		
		groupName = "跟进信息";
		column = addColumn(datagrid, "intentionDegree", "意向度", ControlTypes.ENUM_BOX, 80);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "cooperativeMode", "合作模式", ControlTypes.ENUM_BOX, 80);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "trackProgress", "进度", ControlTypes.ENUM_BOX, 80);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "expectedSign", "预计签单时间", ControlTypes.ENUM_BOX, 100);{
			column.setGroupName(groupName);
		}
		
		column = addColumn(datagrid, "department.name", "所属部门", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "owner.name", "业务员", ControlTypes.DECIMAL_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "nextTrackDate", "下次跟进时间", ControlTypes.DATE_BOX, 100);{
			column.setGroupName(groupName);
		}
		
		column = addColumn(datagrid, "lastTrackTime", "最后跟进时间", ControlTypes.DATETIME_BOX, 130);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "lastTracker", "最后跟进人", ControlTypes.DATE_BOX, 100);{
			column.setGroupName(groupName);
		}
		column = addColumn(datagrid, "lastTrackContent", "最后跟进内容", ControlTypes.TEXT_BOX, 300);{
			column.setGroupName(groupName);
		}	

		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		PFormField formField = null;
		
		String groupName = "基本信息";
		formField = addFormField(form, "name", "公司名称", groupName, ControlTypes.TEXT_BOX, true, false);{
			//formField.setFullColumn(true);
		}
		
		addFormField(form, "legalPerson", "法人", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "employeeCount", "人数", groupName, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "annualIncome", "年收入(万)", groupName, ControlTypes.DECIMAL_BOX, false, false);
		formField = addFormField(form, "province.name", "省份",groupName,ControlTypes.PCC_BOX, true, false);{
			formField.setDataOptions("level:1,changeCtrlId:'city_name'");
		}
		formField = addFormField(form, "city.name", "城市",groupName,ControlTypes.PCC_BOX, true, false);{
			formField.setDataOptions("level:2,changeCtrlId:'county_name'");
		}
		formField = addFormField(form, "county.name", "区/县",groupName,ControlTypes.PCC_BOX, true, false);{
			formField.setDataOptions("level:3");
		}
		
		formField = addFormField(form, "registerAddress", "注册地址", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			formField.setFullColumn(true);
		}

		formField = addFormField(form, "workdAddress", "办公地址", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			formField.setFullColumn(true);
		}

		formField = addFormField(form, "scopes", "经营范围",groupName, ControlTypes.CHECK_BOX_GROUP, false, false);{
			
			formField.setConvertor("scope");
			formField.setDataOptions("rowCount:4,itemMinWidth:200");
			formField.setFullColumn(true);
		}

		formField = addFormField(form, "memoto", "备注", groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setFullColumn(true);
			formField.setHeight(100);
		}
		
		
		groupName = "联系信息";
		addFormField(form, "linkmanName", "联系人", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "mobile", "手机号", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "post", "职务", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "weixin", "微信", groupName, ControlTypes.TEXT_BOX, false, false);
		groupName = "跟进信息";
		
		
		addFormField(form, "intentionDegree", "意向度", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "CooperativeMode", "合作模式", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "trackProgress", "进度", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "expectedSign", "预计签单时间", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormFieldRefrence(form, "department.name", "所属部门", groupName, "Organization-Department", false, false);
		addFormFieldRefrence(form, "owner.name", "业务员", groupName, "Employee", false, false);
		addFormField(form, "nextTrackDate", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "lastTrackTime", "最后跟进时间", groupName, ControlTypes.DATETIME_BOX, false, true);
		
		formField = addFormField(form, "lastTrackContent", "最后跟进内容", groupName, ControlTypes.TEXTAREA, false, true);
		{
			formField.setFullColumn(true);
			formField.setHeight(100);
		}
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {
		
		createLinkmansPart(workspace);
		createTrackPart(workspace);
	}

	private void createLinkmansPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(FranchiseeLinkman.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "联系人信息");
		{
			addColumn(datagrid, "name", "姓名", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "mobile", "手机", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "post", "职务", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "weixin", "微信号", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "main", "主联系人", ControlTypes.BOOLCOMBO_BOX, 80);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("联系人信息");

			PFormField formField = null;
			formField = addFormField(form, "name", "姓名", ControlTypes.TEXT_BOX,true, false);
			{
				formField.setWidth(300);
			}
			formField = addFormField(form, "mobile", "手机", ControlTypes.TEXT_BOX, true, false);
			{
				formField.setWidth(300);
			}
			formField = addFormField(form, "post", "职务", ControlTypes.TEXT_BOX, false, false);
			{
				formField.setWidth(300);
			}
			formField = addFormField(form, "weixin", "微信号", ControlTypes.TEXT_BOX, false, false);
			{
				formField.setWidth(300);
			}
			formField = addFormField(form, "main", "主联系人", ControlTypes.SWITCH_BUTTON, false, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("联系人信息");
			part.setCode("linkmans");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("linkmans");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(550);
			part.setWindowHeight(370);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}

	private void createTrackPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(FranchiseeTrack.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "跟进信息");
		{
			addColumn(datagrid, "nextTrackDate", "下次跟进时间", ControlTypes.DATE_BOX, 130);
			PDatagridColumn column = addColumn(datagrid, "intentionDegree", "意向度", ControlTypes.ENUM_BOX, 100);{

				String formatter = EnumUtil.getColumnFormatter(IntentionDegree.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "trackProgress", "进度", ControlTypes.ENUM_BOX, 100);{

				String formatter = EnumUtil.getColumnFormatter(TrackProgress.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "expectedSign", "预计签单时间", ControlTypes.ENUM_BOX, 100);{

				String formatter = EnumUtil.getColumnFormatter(ExpectedSign.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 500);
		}

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("跟进信息");

			PFormField formField = null;
			formField = addFormField(form, "intentionDegree", "意向度", ControlTypes.ENUM_BOX, true, false);
			{
				formField.setWidth(400);
			}
			formField = addFormField(form, "trackProgress", "进度", ControlTypes.ENUM_BOX, true, false);
			{
				formField.setWidth(400);
			}
			formField = addFormField(form, "expectedSign", "预计签单时间", ControlTypes.ENUM_BOX, true, false);
			{
				formField.setWidth(400);
			}
			formField = addFormField(form, "nextTrackDate", "下次跟进时间", ControlTypes.DATE_BOX, true, false);
			{
				formField.setWidth(400);
			}
			formField = addFormField(form, "content", "内容", ControlTypes.TEXTAREA, true, false);
			{
				formField.setWidth(400);
				formField.setHeight(100);
				formField.setFullColumn(false);
			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("跟进信息");
			part.setCode("tracks");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tracks");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setToolbar("crm/customer/flow/detail");
			part.setJsController(TrackDetailPart.class.getName());
			part.setServiceController(TrackDetailPart.class.getName());
			part.setWindowWidth(600);
			part.setWindowHeight(470);
			part.setForm(form);
		}

		workspace.getParts().add(part);
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "公司名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobile", "手机", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "intentionDegree", "意向度", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "cooperativeMode", "合作模式", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "trackProgress", "进度", ControlTypes.ENUM_BOX);
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
