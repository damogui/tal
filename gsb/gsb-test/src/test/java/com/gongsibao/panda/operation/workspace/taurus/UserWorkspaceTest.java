package com.gongsibao.panda.operation.workspace.taurus;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.taurus.dic.DingtalkKeywordStatus;
import com.gongsibao.entity.taurus.dic.PaymentType;
import com.gongsibao.entity.taurus.dic.WalletType;
import com.gongsibao.taurus.web.UserFormPart;
import com.gongsibao.taurus.web.WalletLogsDetailPart;

public class UserWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/taurus/user/list";
		urlForm = "/taurus/user/form";
		entity = User.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "GSB_TAURUS_" + User.class.getSimpleName();
		formServiceController = UserFormPart.class.getName();
		formJsController = UserFormPart.class.getName();
		formJsImport = "/gsb/taurus/js/user.form.part.js";
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 650;
	}

	/**
	 * @Title: walletLogsDetailPartToolbar
	 * @Description: TODO(充值记录)
	 * @param:
	 * @return: void
	 * @throws
	 */
	@Test
	public void walletLogsDetailPartToolbar() {

		ResourceNode node = this.resourceService.byCode("GSB_TAURUS_" + UserWalletLog.class.getSimpleName());
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("taurus/user/recharge/detail");
			toolbar.setName("子表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-cny fa-fw");
			item.setName("充值");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(1);
			item.setCommand("{controller}.add();");
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
		column = addColumn(datagrid, "mobile", "手机", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		column = addColumn(datagrid, "amount", "余额", ControlTypes.DECIMAL_FEN_BOX, 100);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		addColumn(datagrid, "remark", "备注", ControlTypes.TEXT_BOX, 300);
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		{

			column.setOrderbyMode(OrderbyMode.DESC);
		}
		addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(2);
		}

		String groupName = null;
		addFormField(form, "mobile", "手机号", groupName, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "amount", "余额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, true);
		addFormField(form, "createTime", "创建时间", groupName, ControlTypes.DATETIME_BOX, false, true);
		addFormField(form, "updateTime", "修改时间", groupName, ControlTypes.DATETIME_BOX, false, true);
		addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false, false);

		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		createWalletLogsDetailPart(workspace);
		createCollectCompanysDetailPart(workspace);
		createDingtalkKeywordsDetailPart(workspace);
	}

	private void createWalletLogsDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_TAURUS_" + UserWalletLog.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "钱包记录");
		{
			datagrid.setShowCheckbox(false);
			PDatagridColumn column = null;
			addColumn(datagrid, "paymentNo", "支付流水号", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 150);
			column = addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 80);
			{
				String formatter = EnumUtil.getColumnFormatter(WalletType.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "paymentType", "支付类型", ControlTypes.ENUM_BOX, 80);
			{
				String formatter = EnumUtil.getColumnFormatter(PaymentType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "price", "金额", ControlTypes.DECIMAL_FEN_BOX, 80);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			column = addColumn(datagrid, "id", "操作", ControlTypes.TEXT_BOX, 60);
			{

				String formatter = "return controllerwalletLogs.operationFormatter(value,row);";
				column.setFormatter(formatter);
				column.setAlign(DatagridAlign.CENTER);
			}
		}

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("充值");

			addFormField(form, "paymentType", "支付类型", ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "price", "充值金额", ControlTypes.DECIMAL_BOX, true, false);
			addFormField(form, "discountAmount", "赠送金额", ControlTypes.DECIMAL_BOX, false, false);
			addFormField(form, "remark", "备注", ControlTypes.TEXTAREA, false, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("钱包记录");
			part.setCode("walletLogs");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("walletLogs");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("taurus/user/recharge/detail");
			part.setServiceController(WalletLogsDetailPart.class.getName());
			part.setJsController(WalletLogsDetailPart.class.getName());
			part.setWindowWidth(450);
			part.setWindowHeight(400);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setStyle("height:250px;");
			part.setDockStyle(DockType.TOP);
		}
	}

	private void createCollectCompanysDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_TAURUS_" + UserCollectCompany.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "关注企业");
		{
			datagrid.setShowCheckbox(false);
			addColumn(datagrid, "status", "关注", ControlTypes.BOOLCOMBO_BOX, 80);
			addColumn(datagrid, "companyName", "企业名称", ControlTypes.TEXT_BOX, 300);
			addColumn(datagrid, "remark", "说明", ControlTypes.TEXT_BOX, 300);

		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("关注企业");
			part.setCode("collectCompanys");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("collectCompanys");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}

		workspace.getParts().add(part);
	}

	private void createDingtalkKeywordsDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_TAURUS_" + UserDingtalkKeyword.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "舆情关键字");
		{
			datagrid.setShowCheckbox(false);
			addColumn(datagrid, "keyword", "关键字", ControlTypes.TEXT_BOX, 200);
			PDatagridColumn column = addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(DingtalkKeywordStatus.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("舆情关键字");
			part.setCode("dingtalkKeywords");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("dingtalkKeywords");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}

		workspace.getParts().add(part);
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "mobile", "手机", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
