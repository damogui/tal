package com.gongsibao.panda.platform.operation.workspace.crm.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.crm.web.NCustomerFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.crm.dic.TaskCustomerType;

public class CustomerEditWorkspaceTest extends CustomerAddWorkspaceTest {

	protected String productsDetailResourceNodeCode = "Operation_CRM_Customer_Products";
	protected String foolowDetailResourceNodeCode = "Operation_CRM_Customer_Foolow";
	protected String notifyDetailResourceNodeCode = "Operation_CRM_Customer_Notify";
	protected String changeDetailResourceNodeCode = "Operation_CRM_Customer_Change";

	@Before
	public void setup() {
		super.setup();
		entity = NCustomer.class;
		urlForm = "/crm/platform/customer/edit";
		listPartName = formPartName = "编辑客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_Edit";

		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/supplier/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/platform/operation/crm/js/customer-edit-form.part.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		formJsController = "com.gongsibao.crm.web.NCustomerPlatformEditFormPart";
		formServiceController = NCustomerFormPart.class.getName();

		taskDetailJsController = "com.gongsibao.crm.web.PlatformTaskDetailPart";

		formToolbarPath = "/crm/customer/edit";
	}

	@Test
	public void run() {

		createFormWorkspace();
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, node.getName());
		form.toNew();
		form.setColumnCount(3);
		form.setName(formPartName);

		PFormField formField = null;

		String groupName = null;
		formField = addFormField(form, "realName", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
		{

			formField.setTroikaValidation("['maxLength[50]']");
		}
		addFormField(form, "id", "客户Id", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "sex", "性别", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "mobile", "手机", groupName, ControlTypes.ENCRYPTION_BOX, false, false);
		{

			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("['validationContactWay[\\'mobile\\',\\'手机\\']']");
		}
		formField = addFormField(form, "telephone", "座机", groupName, ControlTypes.ENCRYPTION_BOX, false, false);
		{

			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("['validationContactWay[\\'telephone\\',\\'座机\\']']");
		}
		formField = addFormField(form, "qq", "QQ", groupName, ControlTypes.ENCRYPTION_BOX, false, false);
		{

			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("['validationContactWay[\\'qq\\',\\'QQ\\']']");
		}
		formField = addFormField(form, "weixin", "微信", groupName, ControlTypes.ENCRYPTION_BOX, false, false);
		{

			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("['validationContactWay[\\'weixin\\',\\'微信\\']']");
		}
		formField = addFormField(form, "email", "邮箱", groupName, ControlTypes.ENCRYPTION_BOX, false, false);
		{

			formField.setTroikaValidation("['email','maxLength[50]']");
		}
		addFormField(form, "birdthday", "生日", groupName, ControlTypes.DATE_BOX, false, false);
		addFormField(form, "important", "重要程度", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "province.name", "省份", groupName, ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:1,changeCtrlId:'city_name'");
		}
		formField = addFormField(form, "city.name", "城市", groupName, ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:2,changeCtrlId:'county_name'");
		}

		formField = addFormField(form, "county.name", "区/县", groupName, ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:3");
		}

		formField = addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setFullColumn(true);
			formField.setHeight(50);
			formField.setTroikaValidation("[\'maxLength[500]\']");
		}

		return form;
	}

	@Test
	public void createFormToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/form/edit");
			toolbar.setPath("/crm/customer/edit");
			toolbar.setName("客户修改");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("编辑");
			item.setCommand(null);
			item.setSeq(3000);
			item.setCommand("{controller}.edit();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("eye");
			item.setIcon("fa fa-eye");
			item.setName("查看联系方式");
			item.setCommand(null);
			item.setSeq(4000);
			item.setCommand("{controller}.showContactWay();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	// 默认的表单配置信息
	// protected PForm createForm(ResourceNode node) {
	//
	// PForm form = super.createForm(node);
	// form.setReadOnly(true);
	// String groupName = "最近跟进";
	// addFormField(form, "intentionCategory", "质量分类", groupName,
	// ControlTypes.ENUM_BOX, false, true);
	// addFormFieldRefrence(form, "quality.name", "质量", groupName,
	// NCustomerTaskQuality.class.getSimpleName(), false, true);
	// addFormFieldRefrence(form, "lastFoolowUser.name", "最近跟进人", groupName,
	// Employee.class.getSimpleName(), false, true);
	// addFormField(form, "lastFollowTime", "最近跟进时间", groupName,
	// ControlTypes.DATETIME_BOX, false, true);
	// addFormField(form, "nextFoolowTime", "下次跟进时间", groupName,
	// ControlTypes.DATETIME_BOX, false, true);
	// PFormField field = addFormField(form, "lastContent", "最近跟进内容", groupName,
	// ControlTypes.TEXTAREA, false, true);{
	//
	// field.setHeight(100);
	// }
	//
	// return form;
	// }

	protected void addDetailGridPart(PWorkspace workspace) {

		addFlowLogPart(workspace);

		// 日志信息
		addCommunicatLogsPart(workspace);

		addNotificationLogPart(workspace);

		// 客户商机
		createTasksPart(workspace);

		// 意向产品
		addIntenProductPart(workspace);

		createCompanysDetailPart(workspace);

	}
	// 重新父类客户商机
	public void createTasksPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(taskDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "商机信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			
			addColumn(datagrid, "id", "商机Id", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "creator", "创建人", ControlTypes.DATETIME_BOX, 100);
			PDatagridColumn column = addColumn(datagrid, "taskType", "类型", ControlTypes.ENUM_BOX, 100, false);{
				
				String formatter = EnumUtil.getColumnFormatter(TaskCustomerType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, false);
			addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "department.name", "分配部门", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "owner.name", "分配业务员", ControlTypes.TEXT_BOX, 100, false);
			column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100, false);{
				
				String formatter = EnumUtil.getColumnFormatter(CustomerFollowStatus.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "remark", "售前备注", ControlTypes.TEXT_BOX, 300, false);
			addColumn(datagrid, "smsRemark", "短信备注", ControlTypes.TEXT_BOX, 300, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("商机信息");
			part.setCode("tasks");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tasks");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			
			part.setJsController(taskDetailJsController);
		}
		workspace.getParts().add(part);
		
		part = workspace.getParts().get(0);
		{
			part.setName("新增客户");
			part.setDockStyle(DockType.TOP);
			part.setHeight(500);
		}
	}	
	// 意向产品
	private void addIntenProductPart(PWorkspace workspace) {
		// 需要配置NCustomerProduct资 源
		ResourceNode node = this.resourceService.byCode(productsDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "意向产品");
		{
			datagrid.setShowCheckbox(false);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "creator", "创建人", ControlTypes.DATETIME_BOX, 100);
			addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 300);
			addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 150);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("意向产品");
			part.setCode("products");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("products");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		workspace.getParts().add(part);

	}

	// 选项卡加载项
	private void addCommunicatLogsPart(PWorkspace workspace) {
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode(foolowDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "跟进日志");
		{
			datagrid.setNowrap(false);
			datagrid.setShowCheckbox(false);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "creator", "创建人", ControlTypes.DATETIME_BOX, 100);
			PDatagridColumn column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(CustomerFollowStatus.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 150);
			addColumn(datagrid, "signingAmount", "估计签单金额", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "returnedAmount", "估计回款金额", ControlTypes.DECIMAL_FEN_BOX, 150);

			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 400);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("跟进日志");
			part.setCode("follows");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("follows");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		workspace.getParts().add(part);
	}

	private void addNotificationLogPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(notifyDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "通知日志");
		{
			datagrid.setShowCheckbox(false);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "creator", "创建人", ControlTypes.DATETIME_BOX, 100);
			PDatagridColumn column = addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(NotifyType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 400);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("通知日志");
			part.setCode("notifys");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("notifys");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		workspace.getParts().add(part);
	}

	private void addFlowLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(changeDetailResourceNodeCode);

		PDatagrid datagrid = new PDatagrid(node, "操作日志");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setNowrap(false);
			// 子页面枚举显示需要格式化一下
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "creator", "创建人", ControlTypes.DATETIME_BOX, 100);
			PDatagridColumn column = addColumn(datagrid, "changeType", "操作类型", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(ChangeType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "formUser.name", "来自", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "toUser.name", "去向", ControlTypes.NUMBER_BOX, 100);
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 400);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("操作日志");
			part.setCode("changes");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("changes");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		{
			part.setName("客户信息");
			part.setDockStyle(DockType.TOP);
		}
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.update);
	}
}
