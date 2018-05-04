package com.gongsibao.panda.supplier.igirl.workspace.ic;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.ExcelBaseInfo;
import com.gongsibao.entity.igirl.ic.ex.dict.MemberType;
import com.gongsibao.igirl.ic.web.*;
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
 * @ClassName:  IcExExtraInfoWorkspaceTest
 * @Description: 公司登记信息
 * @author: 周瑞帆
 * @date:   2018.5.4
 *
 */
public class IcExExtraInfoWorkspaceTest extends WorkspaceCreationBase{
	@Before
	public void setup() {
		super.setup();
		urlList = "/igirl/ic/ExcelBaseInfo/ex/list";
		urlForm = "/igirl/ic/ExcelBaseInfo/ex//form";
		entity = ExcelBaseInfo.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_REGIST_ExcelBaseInfo";
		listPartName = formPartName = meta.getName();
		listToolbarPath="/igirl/ic/ExExtraInfo/list";
		listPartImportJs = "/gsb/igirl/js/icexcelextarinfo.list.part.js";
		listPartServiceController = IcExcelExtraInfoListPart.class.getName();
		listPartJsController = IcExcelExtraInfoListPart.class.getName();

		formServiceController = IcExcelBaseInfoPart.class.getName();
		formJsController = IcExcelBaseInfoPart.class.getName();
		formJsImport = "/gsb/igirl/js/icexcelbaseinfo.form.part.js";
	}

	private String icCaListToolbarPath = "/igirl/ic/CorporateAddress/list";

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
			item.setIcon("fa fa-edit");
			item.setName("公司登记");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.updateState();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);

		node = this.resourceService.byCode("IGRIL_IC_REGIST_CorporateAddress");
		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(icCaListToolbarPath);
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
		column = addColumn(datagrid, "isDataReady", "是否进行材料准备", ControlTypes.TEXT_BOX, 200);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("if( row.isDataReady==true){ return '是' } else{ return '否' }");
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
			form.setName("登记信息");
			form.setColumnCount(1);
		}
		PFormField formField = null;
		addFormField(form, "companyName", "公司名称", "", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "operationScope", "经营范围", "", ControlTypes.TEXTAREA, false, false);
		addFormField(form, "remark", "备注", "", ControlTypes.TEXTAREA, false, false);
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		super.addDetailGridPart(workspace);
		createCorporateAddressDetailPart(workspace);
		createMemberDetailPart(workspace);
		createWorkerDetailPart(workspace);
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
			part.setToolbar("/igirl/ic/Mermber/list");
			part.setJsController("com.gongsibao.igirl.ic.web.MemberDetailPart");
			part.setServiceController(MemberDetailPart.class.getName());
			part.setWindowWidth(420);
			part.setWindowHeight(600);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	private void createWorkerDetailPart(PWorkspace workspace){
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_Worker");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "职员信息");
		{
			addColumn(datagrid, "memberName", "姓名", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			addColumn(datagrid, "memberMobile", "手机", ControlTypes.TEXT_BOX, 180).setAlign(DatagridAlign.CENTER);
			column = addColumn(datagrid, "position", "职位", ControlTypes.ENUM_BOX, 180);
			{
				column.setAlign(DatagridAlign.CENTER);
				String formatter = EnumUtil.getColumnFormatter(MemberType.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "isNeed", "是否完善", ControlTypes.ENUM_BOX, 180);
			{
				column.setAlign(DatagridAlign.CENTER);
				column.setFormatter("if( row.isNeed==true){ return '是' } else{ return '否' }");
				//TODO 状态不修改，待完善
			}
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("职员信息");
			form.setColumnCount(1);
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "position", "职位", groupName, ControlTypes.ENUM_BOX, true, true);
			formField = addFormField(form, "memberMobile", "手机", groupName, ControlTypes.TEXT_BOX, true, false);
			{
				formField.setTroikaTrigger("controllerworkers.isTel(this);");
			}
			addFormField(form, "memberName", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "isNeed", "是否选定完善", groupName, ControlTypes.SWITCH_BUTTON, true, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("职员信息");
			part.setCode("workers");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("workers");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setJsController("com.gongsibao.igirl.ic.web.WorkerDetailPart");
			part.setServiceController(WorkerDetailPart.class.getName());
			part.setWindowWidth(420);
			part.setWindowHeight(400);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	private void createCorporateAddressDetailPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_CorporateAddress");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "企业地址");
		{
			addColumn(datagrid, "workAddress", "实际办公地址", ControlTypes.TEXTAREA, 300).setAlign(DatagridAlign.CENTER);
			column = addColumn(datagrid, "ownLandType", "是否为自有地", ControlTypes.ENUM_BOX, 180);
			{
				column.setFormatter("if( row.ownLandType==true){ return '是' } else{ return '否' }");
			}
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("企业地址");
			form.setColumnCount(1);
			String groupName = null;
			PFormField formField = null;
			formField = addFormField(form, "ownLandType", "是否为自有地", groupName, ControlTypes.SWITCH_BUTTON, true, false);
			{
				formField.setTroikaTrigger("controllercorporateAddresses.ownLandTypeChange(newValue,oldValue);");
			}
			groupName = "自有地";
			addFormField(form, "ownLandAddress", "自有地址", groupName, ControlTypes.TEXTAREA, false, false);
			addFormField(form, "landType", "住所情况", groupName, ControlTypes.TEXT_BOX, false, false);
			addFormField(form, "landArea", "房屋面积", groupName, ControlTypes.TEXT_BOX, false, false);
			addFormField(form, "isBelongWestDistrict", "是否属于中关村西区管辖", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "landOwner", "房屋产权人", groupName, ControlTypes.TEXT_BOX, false, false);
			addFormField(form, "streetOffice", "街道办事处", groupName, ControlTypes.TEXT_BOX, false, false);
			groupName = "供地";
			addFormField(form, "ourAddress", "提供地址", groupName, ControlTypes.TEXT_BOX, false, false);
			addFormField(form, "workAddress", "实际办公地址", null, ControlTypes.TEXTAREA, true, false);
			addFormField(form, "state", "选定地址", null, ControlTypes.SWITCH_BUTTON, true, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("企业地址");
			part.setCode("corporateAddresses");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("corporateAddresses");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icCaListToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.CorporateAddressDetailPart");
			part.setServiceController(CorporateAddressDetailPart.class.getName());
			part.setWindowWidth(450);
			part.setWindowHeight(650);
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


