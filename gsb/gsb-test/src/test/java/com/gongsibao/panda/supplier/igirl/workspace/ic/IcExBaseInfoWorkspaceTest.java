package com.gongsibao.panda.supplier.igirl.workspace.ic;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import com.gongsibao.entity.igirl.ic.ex.dict.IndustryType;
import com.gongsibao.entity.igirl.ic.ex.dict.OrganizationalType;
import com.gongsibao.igirl.ic.web.*;
import com.gongsibao.panda.supplier.igirl.workspace.ic.reference.MemberReferenceTest;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

/**
 * @ClassName:  IcExBaseInfoWorkspaceTest
 * @Description: 工商核名信息
 * @author: 周瑞帆
 * @date:   2018.5.2
 *
 */
public class IcExBaseInfoWorkspaceTest extends WorkspaceCreationBase{
	@Before
	public void setup() {
		super.setup();
		urlList = "/igirl/ic/ExcelBaseInfo/all/list";
		urlForm = "/igirl/ic/ExcelBaseInfo/form";
		entity = ExcelBaseInfo.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_REGIST_ExcelBaseInfo";
		listPartName = formPartName = meta.getName();
		listToolbarPath="/igirl/ic/ExcelBaseInfo/list";
		listPartImportJs = "/gsb/igirl/js/icexcelbaseinfo.list.part.js";
		listPartServiceController = IcExcelBaseInfoListPart.class.getName();
		listPartJsController = IcExcelBaseInfoListPart.class.getName();

		formServiceController = IcExcelBaseInfoPart.class.getName();
		formJsController = IcExcelBaseInfoPart.class.getName();
		formJsImport = "/gsb/igirl/js/icexcelbaseinfo.form.part.js";
	}

	private String icCnListToolbarPath = "/igirl/ic/CompanyName/list";
	private String icMermberListToolbarPath = "/igirl/ic/Mermber/list";
	private String icShareholderCnListToolbarPath = "/igirl/ic/Shareholder/list";

	/*按钮*/
	@Test
	public void fromToolbar() {

		ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("编辑");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.edit();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("updateState");
			item.setIcon("fa fa-trash-o");
			item.setName("选定抓取");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.updateState();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);

		node = this.resourceService.byCode("IGRIL_IC_REGIST_CompanyName");
		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(icCnListToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("updateState");
			item.setIcon("fa fa-trash-o");
			item.setName("选定名称");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.updateState();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);


		node = this.resourceService.byCode("IGRIL_IC_REGIST_Member");
		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(icMermberListToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);

		node = this.resourceService.byCode("IGRIL_IC_REGIST_Shareholder");
		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(icShareholderCnListToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	/*首页显示表格*/
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "companyName", "选定名称", ControlTypes.TEXT_BOX, 300);
		column.setAlign(DatagridAlign.CENTER);
		column = addColumn(datagrid, "customerName", "客户姓名", ControlTypes.TEXT_BOX, 200);
		column.setAlign(DatagridAlign.CENTER);
		column = addColumn(datagrid, "capital", "注册资金(万元)", ControlTypes.TEXT_BOX, 200);
		column.setAlign(DatagridAlign.CENTER);
		column = addColumn(datagrid, "state", "状态", ControlTypes.ENUM_BOX, 200);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("if( row.state==true){ return '是' } else{ return '否' }");
		}
		column = addColumn(datagrid, "createTime", "时间", ControlTypes.TEXT_BOX, 200);
		column.setAlign(DatagridAlign.CENTER);
		column.setOrderbyMode(OrderbyMode.DESC);
		return datagrid;
	}


	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("基础信息");
			form.setColumnCount(1);
		}
		PFormField formField = null;
		formField = addFormField(form, "customerMobile", "客户电话", null, ControlTypes.TEXT_BOX, true,false);
		{
			formField.setTroikaTrigger("controllerexcelBaseInfo.isTel(this);");
		}
		addFormField(form, "customerName", "客户姓名", null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "source", "客户来源", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "consultWay", "咨询途径", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "capital", "注册资金(万元)", "", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "period", "经营期限", "", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "payDate", "注册资本实缴日期", "", ControlTypes.TEXT_BOX, false, false);
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		super.addDetailGridPart(workspace);
		createCompanyNameDetailPart(workspace);
		createMemberDetailPart(workspace);
		createShareholderDetailPart(workspace);
	}

	private void createShareholderDetailPart(PWorkspace workspace){
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_Shareholder");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "股东信息");
		{
			addColumn(datagrid, "memberMobile", "股东电话", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "memberName", "股东姓名", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "amount", "出资金额", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "ratio", "出资比例", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("股东信息");
			form.setColumnCount(1);
			String groupName = null;
			PFormField formField = null;
			formField = addFormField(form, "memberMobile", "股东电话", groupName, ControlTypes.TEXT_BOX, true, false);
			{
				formField.setTroikaTrigger("controllershareholders.isTel(this);");
			}
			addFormField(form, "memberName", "股东姓名", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "amount", "出资金额", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "ratio", "出资比例", groupName, ControlTypes.TEXT_BOX, true, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("股东信息");
			part.setCode("shareholders");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("shareholders");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icShareholderCnListToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.ShareholderDetailPart");
			part.setServiceController(ShareholderDetailPart.class.getName());
			part.setWindowWidth(420);
			part.setWindowHeight(600);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	private void createMemberDetailPart(PWorkspace workspace){
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_Member");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "成员信息");
		{
			addColumn(datagrid, "name", "姓名", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "mobile", "手机", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "email", "邮箱", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "identify", "身份证号", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("成员信息");
			form.setColumnCount(1);
			String groupName = null;
			PFormField formField = null;
			formField = addFormField(form, "mobile", "手机", groupName, ControlTypes.TEXT_BOX, true, false);
			{
				formField.setTroikaTrigger("controllermembers.isTel(this);");
			}
			addFormField(form, "name", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "email", "邮箱地址", groupName, ControlTypes.TEXT_BOX, true, false);
			formField = addFormField(form, "identify", "身份证号", groupName, ControlTypes.TEXT_BOX, true, false);
			{
				formField.setTroikaTrigger("controllermembers.isIdentify(this)");
			}
			addFormField(form, "telephone", "固定电话", groupName, ControlTypes.TEXT_BOX, false, false);
			addFormField(form, "education", "学历", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "address", "住宅地址", groupName, ControlTypes.TEXT_BOX, false, false);
			addFormField(form, "idAddress", "身份证地址", groupName, ControlTypes.TEXT_BOX, true, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("成员信息");
			part.setCode("members");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("members");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icMermberListToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.MemberDetailPart");
			part.setServiceController(MemberDetailPart.class.getName());
			part.setWindowWidth(420);
			part.setWindowHeight(600);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	private void createCompanyNameDetailPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_CompanyName");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "备选公司名称");
		{
			addColumn(datagrid, "name", "公司名称", ControlTypes.TEXT_BOX, 300).setAlign(DatagridAlign.CENTER);
			column = addColumn(datagrid, "state", "是否为选定名称", ControlTypes.TEXT_BOX, 180);
			{
				column.setAlign(DatagridAlign.CENTER);
				column.setFormatter("if( row.state==true){ return '是' } else{ return '否' }");
			}
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("备选公司名称");
			form.setColumnCount(1);
			String groupName = null;
			PFormField formField = null;
			formField = addFormField(form, "entTra", "公司字号", groupName, ControlTypes.TEXT_BOX, true, false);
			{
				formField.setTroikaTrigger("controllercompanyNames.entTraChange(this);");
			}
			addFormField(form, "industryType", "行业特点", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "organizationalType", "组织形式", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "type", "名称结构", groupName, ControlTypes.ENUM_BOX, true, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("备选公司名称");
			part.setCode("companyNames");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("companyNames");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icCnListToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.CompanyNameDetailPart");
			part.setServiceController(CompanyNameDetailPart.class.getName());
			part.setWindowWidth(420);
			part.setWindowHeight(400);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "capital", "注册资金(万元)", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "state", "状态", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "companyName", "选定名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}


}


