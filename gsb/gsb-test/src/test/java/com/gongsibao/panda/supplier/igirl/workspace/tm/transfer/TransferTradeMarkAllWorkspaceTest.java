package com.gongsibao.panda.supplier.igirl.workspace.tm.transfer;

import com.gongsibao.entity.igirl.tm.TransferTradeMark;
import com.gongsibao.igirl.tm.web.TransferTradeMarkListPart;
import com.gongsibao.igirl.tm.web.TransferTradeMarkPart;
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

public class TransferTradeMarkAllWorkspaceTest extends WorkspaceCreationBase {

	@Override
	public void setup() {
		super.setup();
		urlList = "/igirl/transfertrademark/all/list";
		urlForm = "/igirl/transfertrademark/all/form";
		entity = TransferTradeMark.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "IGIRL_All_TransferTradeMark";
		listPartServiceController=TransferTradeMarkListPart.class.getName();
		listToolbarPath = "/igirl/transfer/toolbar";
		formJsController = TransferTradeMarkPart.class.getName();
		formJsImport = "/gsb/igirl/js/transfertrademark.form.part.js";
	}
	public static final String transferTrademarkToolbarPath = "/igirl/ttm/toolbar";

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
		
		node = this.resourceService.byCode(resourceNodeCode);
		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			// toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(transferTrademarkToolbarPath);
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
		addColumn(datagrid, "createTime", "日期", ControlTypes.DATETIME_BOX, 100, true);
		addColumn(datagrid, "agentFileNum", "代理文号", ControlTypes.TEXT_BOX, 120, true);
		//addColumn(datagrid, "assigneeCnName", "转让人名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid,"transferTradeMarkState","状态",ControlTypes.ENUM_BOX,100);
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

		String groupName = "业务信息";
		addFormField(form, "transferType", "选择办理业务", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "certCode", "统一社会信用代码", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "assigneeGjdq", "转让人国籍", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "assigneeCnName", "转让人名称(中文)", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "assigneeEnName", "转让人名称(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "assigneeCnAddr", "转让人地址(中文)", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "assigneeEnAddr", "转让人地址(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "appGjdq", "受让人国籍", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "assignorCnName", "受让人名称(中文)", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "assignorEnName", "受让人名称(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "assignorCnAddr", "受让人地址(中文)", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "assignorEnAddr", "受让人地址(英文)", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "assigneeContactZip", "邮政编码", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "assigneeContactPerson", "联系人", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "assigneeContactTel", "联系电话", groupName, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "agentPerson", "代理人姓名", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "fileWtName", "转让人委托书", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "fileWtName2", "受让人委托书", groupName, ControlTypes.OSS_UPLOAD, true, false);

		formField = addFormField(form, "zdllx", "转让人类型", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllertransferTradeMark.typeChange(newValue, oldValue);");
		}

		formField = addFormField(form, "sdjlx", "受让人类型", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllertransferTradeMark.typeChange(newValue, oldValue);");
		}
		formField = addFormField(form, "zwjlx", "转让人上传文件的语言类型", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllertransferTradeMark.typeChange(newValue, oldValue);");
		}
		formField = addFormField(form, "swjlx", "受让人上传文件的语言类型", groupName, ControlTypes.ENUM_BOX, true, false);
		{
			formField.setTroikaTrigger("controllertransferTradeMark.typeChange(newValue, oldValue);");
		}
		addFormField(form, "gqcxName", "自然人死亡/企业或其他组织注销证明", groupName, ControlTypes.OSS_UPLOAD, false, false);

		addFormField(form, "zrZtCnTrName", "转让人主体资格证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "zrZtEnTrName", "转让人主体资格证明文件(外文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "srZtCnTrName", "受让人主体资格证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "srZtEnTrName", "受让人主体资格证明文件(外文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "certType", "转让人证件名称", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "certNo", "转让人证件号码", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "scertType", "受让人证件名称", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "scertNo", "受让人证件号码", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "zrSfCnTrName", "转让人身份证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "srSfCnTrName", "受让人身份证明文件(中文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "zrSfEnTrName", "转让人身份证明文件(外文)", groupName, ControlTypes.OSS_UPLOAD, false, false);

		addFormField(form, "srSfEnTrName", "受让人身份证明文件(外文)", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "flwsName", "同意转让声明或商标移转证明", groupName, ControlTypes.OSS_UPLOAD, true, false);

		addFormField(form, "tmType", "选择商标类型", groupName, ControlTypes.ENUM_BOX, true, false);

		addFormField(form, "apporregNum", "商标注册号", groupName, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "fileYgName", "有关说明文件", groupName, ControlTypes.OSS_UPLOAD, false, false);

		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "agentFileNum", "代理文号", ControlTypes.TEXT_BOX);
		//addQueryItem(queryProject, "assigneeCnName", "转让人名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "transferTradeMarkState", "状态", ControlTypes.ENUM_BOX);
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
