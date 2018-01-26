package com.gongsibao.panda.operation.workspace.crm.form;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
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
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.web.NCustomerTaskEditFormPart;
import com.gongsibao.crm.web.TaskFollowDetailPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.crm.dic.QualityCategory;

public class TaskEditWorkspaceTest extends TaskAddWorkspaceTest {

	
	protected String foolowDetailResourceNodeCode = "GSB_CRM_Customer_Manager_Foolow";
	protected String notifyDetailResourceNodeCode = "GSB_CRM_Customer_Manager_Notify";
	protected String changeDetailResourceNodeCode = "GSB_CRM_Customer_Manager_Change";
	
	@Before
	public void setup() {
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/operation/task/edit";
		listPartName = formPartName = "任务信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Task_Manager_Edit";
		formJsImport = "/gsb/crm/platform/js/task-add-form.part.js|/gsb/crm/platform/js/task-edit-form.part.js|/gsb/gsb.customer.controls.js";
		formJsController = NCustomerTaskEditFormPart.class.getName();
		formServiceController = NCustomerTaskEditFormPart.class.getName();
	}
	
	@Test
	public void detailPart() {
		
		ResourceNode node = this.resourceService.byCode(foolowDetailResourceNodeCode);
		//OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("crm/task/communicat/detail");
			toolbar.setName("跟进操作");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-mail-reply-all");
			item.setName("跟进");
			item.setCommand(null);
			//item.setOperationType(ot1);
			item.setSeq(1);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	// 创建选项卡
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		addCommunicatLogsPart(workspace);
		createProductsPart(workspace);
		addNotificationLogPart(workspace);
		addFlowLogPart(workspace);
	}

	// 选项卡加载项
	public void addCommunicatLogsPart(PWorkspace workspace) {
		
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode(foolowDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "沟通日志");
		{
			PDatagridColumn column = addColumn(datagrid, "foolowStatus", "状态", ControlTypes.ENUM_BOX, 180);
			{
				String formatter = EnumUtil.getColumnFormatter(CustomerFollowStatus.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "qualityCategory", "质量分类", ControlTypes.ENUM_BOX, 180);
			{
				String formatter = EnumUtil.getColumnFormatter(QualityCategory.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 200);
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
			part.setToolbar("crm/task/communicat/detail");
			part.setJsController(TaskFollowDetailPart.class.getName());
			part.setServiceController(TaskFollowDetailPart.class.getName());
		}
		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:350px;");
		}
	}

	public void addNotificationLogPart(PWorkspace workspace) {
		
		ResourceNode node = this.resourceService.byCode(notifyDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "通知日志");
		{
			PDatagridColumn column = addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 300);
			{
				String formatter = EnumUtil.getColumnFormatter(NotifyType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("通知日志");
			PFormField formField = null;
			String groupName = null;

			formField = addFormField(form, "type", "通知类型", groupName, ControlTypes.ENUM_BOX, false, true);
			formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXT_BOX, false, true);
			{
				formField.setFullColumn(true);
			}
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
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}

	public void addFlowLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(changeDetailResourceNodeCode);

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
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("流转日志");
			PFormField formField = null;
			String groupName = null;
			formField = addFormField(form, "changeType", "流转类型", groupName, ControlTypes.ENUM_BOX, false, true);
			formField = addFormFieldRefrence(form, "formUser.name", "最后分配人", groupName, "CRM_Employee", false, true);
			formField = addFormFieldRefrence(form, "toUser.name", "最后分配人", groupName, "CRM_Employee", false, true);
			formField = addFormField(form, "content", "内容", groupName, ControlTypes.TEXT_BOX, false, true);
			{
				formField.setFullColumn(true);
			}
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
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
}
