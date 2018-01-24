package com.gongsibao.panda.operation.workspace.crm.form;

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
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.web.NCustomerEditFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;

public class CustomerEditWorkspaceTest extends CustomerAddWorkspaceTest{


	@Before
	public void setup() {
		super.setup();
		entity = NCustomer.class;
		urlForm = "/operation/customer/edit";
		listPartName = formPartName = "客户信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_Edit";
		formJsImport = "/gsb/crm/js/customer-add-form.part.js|/gsb/crm/js/customer-edit-form.part.js|/gsb/gsb.customer.controls.js";
		formJsController = NCustomerEditFormPart.class.getName();
		formServiceController = NCustomerEditFormPart.class.getName();
	}
	

	@Test
	public void run() {

		createFormWorkspace();
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		String groupName = "最近跟进";
		addFormField(form, "intentionCategory", "质量分类", groupName, ControlTypes.ENUM_BOX, false, true);
		//addFormFieldRefrence(form, "intention", "质量", groupName, "", false, true);
		addFormField(form, "lastFoolowUser.name", "最近跟进人", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "lastFollowTime", "最近跟进时间", groupName, ControlTypes.DATETIME_BOX, false, true);
		addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATETIME_BOX, false, true);
		PFormField field = addFormField(form, "lastContent", "最近跟进内容", groupName, ControlTypes.TEXTAREA, false, true);{
			
			field.setHeight(100);
		}
		
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		// 客户任务
		super.createTasksPart(workspace);
		
		// 意向产品
		addIntenProductPart(workspace);
		
		// 日志信息
		addCommunicatLogsPart(workspace);
		
		addNotificationLogPart(workspace);
		
		addFlowLogPart(workspace);
	}

	

	// 意向产品
	private void addIntenProductPart(PWorkspace workspace) {
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode("GSB_CRM_Customer_Manager_Products");
		PDatagrid datagrid = new PDatagrid(node, "意向产品");
		{
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
		ResourceNode node = this.resourceService.byCode(NCustomerTaskFoolow.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "沟通日志");
		{
			PDatagridColumn column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 300);
			{
				String formatter = EnumUtil.getColumnFormatter(CustomerFollowStatus.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 150);
			addColumn(datagrid, "estimateAmount", "估计签单金额", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 150);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("沟通日志");
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
		
		ResourceNode node = this.resourceService.byCode(NCustomerTaskNotify.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "通知日志");
		{
			PDatagridColumn column = addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 300);
			{
				String formatter = EnumUtil.getColumnFormatter(NotifyType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 150);
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
		ResourceNode node = this.resourceService.byCode(NCustomerChange.class.getSimpleName());

		PDatagrid datagrid = new PDatagrid(node, "流转日志");
		{
			// 子页面枚举显示需要格式化一下
			PDatagridColumn column = addColumn(datagrid, "changeType", "流转类型", ControlTypes.ENUM_BOX, 300);
			{
				String formatter = EnumUtil.getColumnFormatter(ChangeType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "formUser.name", "来自", ControlTypes.ENUM_BOX, 150);
			addColumn(datagrid, "toUser.name", "去向", ControlTypes.NUMBER_BOX, 150);
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 150);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("流转日志");
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
			part.setDockStyle(DockType.DOCUMENTHOST);
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
