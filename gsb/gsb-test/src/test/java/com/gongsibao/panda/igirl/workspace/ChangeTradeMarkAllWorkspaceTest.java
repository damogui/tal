package com.gongsibao.panda.igirl.workspace;
import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.igirl.web.*;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ChangeTradeMarkAllWorkspaceTest extends WorkspaceCreationBase {

	@Override
	public void setup() {
		super.setup();
		urlList = "/igirl/changetrademark/all/list";
		urlForm = "/igirl/changetrademark/all/form";
		entity = ChangeTradeMark.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "IGIRL_All_ChangeTradeMark";
		listPartServiceController=ChangeTradeMarkListPart.class.getName();
		listToolbarPath = "/igirl/list/toolbar";
		formJsController = ChangeTradeMarkPart.class.getName();
		formJsImport = "/gsb/igirl/js/changetrademark.form.part.js";
	}
	public static final String changeTrademarkToolbarPath = "/igirl/ctm/toolbar";

	@Test
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("案件工具栏");
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

		toolbarService.save(toolbar);
		
		node = this.resourceService.byCode("IGIRL_All_ChangeTradeMark");
		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			// toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(changeTrademarkToolbarPath);
			toolbar.setName("商标选项工具栏");
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
			item.setCode("copy");
			item.setIcon("fa fa-copy");
			item.setName("复制");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.copy();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("save");
			item.setIcon("fa fa-save");
			item.setName("保存");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.save();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);

	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "createTime", "日期", ControlTypes.DATETIME_BOX, 100, true);
		addColumn(datagrid, "agentFileNum", "代理文号", ControlTypes.TEXT_BOX, 120, true);
		addColumn(datagrid, "txt_sqrmyzw", "申请人名称", ControlTypes.TEXT_BOX, 200);
		return datagrid;
	}

	//
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, this.formPartName);
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(1);
		}

		PFormField formField = null;

		String groupName = "申请人信息";
		addFormField(form, "writeType", "申请人国籍", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "certCode", "统一社会信用代码", groupName, ControlTypes.TEXT_BOX, false, false);

		formField = addFormField(form, "txt_sqrmyzw", "申请人名称(中文)", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			formField.setTroikaTrigger("controllerchangeTradeMark.nameChange(this);");
		}

		addFormField(form, "txt_sqrmyyw", "申请人名称(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		formField = addFormField(form, "txt_sqrdzzw", "申请人地址(中文)", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			formField.setTroikaTrigger("controllerchangeTradeMark.txt_sqrdzzwChange(this);");
		}

		addFormField(form, "txt_sqrdzyw", "申请人地址(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "txt_yzbm", "邮政编码", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "txt_lxr", "联系人", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "txt_dh", "联系电话", groupName, ControlTypes.TEXT_BOX, false, false);

		groupName = "代理人信息";

		addFormField(form, "txt_dlrmc", "代理人姓名", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "agentBookPath", "代理委托书", groupName, ControlTypes.OSS_UPLOAD, true, false);

		formField = addFormField(form, "applierType", "申请人类型", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllerchangeTradeMark.applierTypeChange(newValue, oldValue);");
		}

		formField = addFormField(form, "languageType", "语言类型", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllerchangeTradeMark.languageTypeChange(newValue, oldValue);");
		}

		addFormField(form, "certFilePath", "主体资格证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, false, false);

		addFormField(form, "certFileENPath", "主体资格证明原文件(外文)", groupName, ControlTypes.OSS_UPLOAD, false, false);

		addFormField(form, "certificateType", "证件名称", groupName, ControlTypes.ENUM_BOX, false, false);

		addFormField(form, "appCertificateNum", "证件号码", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "appCertFilePath", "身份证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, false, false);

		addFormField(form, "appCertFileENPath", "身份证明原文件(外文)", groupName, ControlTypes.OSS_UPLOAD, false, false);

		groupName = "业务信息";

		addFormField(form, "changeType", "变更类型", groupName, ControlTypes.ENUM_BOX, true, false);

		formField = addFormField(form, "txt_bgqmyzw", "变更前名称(中文)", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			{
				formField.setTroikaTrigger("controllerchangeTradeMark.nameChange(newValue, oldValue);");
			}
		}

		addFormField(form, "txt_bgqmyyw", "变更前名称(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "txt_bgqdzzw", "变更前地址(中文)", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "txt_bgqdzyw", "变更前地址(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		formField = addFormField(form, "proveLanguageType", "变更证明文件是否为中文", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllerchangeTradeMark.proveLanguageTypeChange(newValue, oldValue);");
		}

		addFormField(form, "bgzmFilePath", "变更证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "bgzmFileENPath", "变更证明文件(原件)", groupName, ControlTypes.OSS_UPLOAD, false, false);

		addFormField(form, "changeTradeMarkType", "商标类型", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "txt_sbsqh", "商标申请号/注册号", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "commentPath", "有关说明文件", groupName, ControlTypes.OSS_UPLOAD, false, false);

		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "agentFileNum", "代理文号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "txt_sqrmyzw", "申请人名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "changeTradeMarkState", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "createTime", "时间", ControlTypes.DATE_BOX).setWidth(400);
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
