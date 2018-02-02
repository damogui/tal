package com.gongsibao.panda.operation.workspace.crm.form;

import java.util.ArrayList;
import java.util.List;

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
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerTaskEditFormPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.crm.dic.TaskInspectionState;
import com.gongsibao.entity.crm.dic.TaskInspectionType;
import com.gongsibao.entity.product.Product;

public class TaskEditWorkspaceTest extends TaskAddWorkspaceTest {

	
	protected String foolowDetailResourceNodeCode = "Operation_CRM_Customer_Foolow";
	protected String notifyDetailResourceNodeCode = "Operation_CRM_Customer_Notify";
	protected String changeDetailResourceNodeCode = "Operation_CRM_Customer_Change";
	
	protected String taskFollowDetailPart = "com.gongsibao.crm.web.PlatformTaskFollowDetailPart";
	
	protected String inspectionDetailResourceNodeCode = "Operation_CRM_Customer_Inspection";
	@Before
	public void setup() {
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/crm/platform/task/edit";
		listPartName = formPartName = "任务信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Task_Edit";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/platform/js/task-add-form.part.js");
		ss.add("/gsb/crm/base/js/task-base-edit-form.part.js");
		ss.add("/gsb/crm/platform/js/task-edit-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = NCustomerTaskEditFormPart.class.getName();
		formServiceController = NCustomerTaskEditFormPart.class.getName();
		
		//task-base-edit-form.part.js
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
		addInspectionLogPart(workspace);
	}

	// 选项卡加载项
	public void addCommunicatLogsPart(PWorkspace workspace) {
		
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode(foolowDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "跟进日志");
		{
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			PDatagridColumn column = addColumn(datagrid, "qualityCategory", "质量分类", ControlTypes.ENUM_BOX, 180);
			{
				String formatter = EnumUtil.getColumnFormatter(QualityCategory.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 200);
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
			part.setToolbar("crm/task/communicat/detail");
			part.setJsController(taskFollowDetailPart);
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
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			PDatagridColumn column = addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(NotifyType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 400);
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
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			// 子页面枚举显示需要格式化一下
			PDatagridColumn column = addColumn(datagrid, "changeType", "流转类型", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(ChangeType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "formUser.name", "来自", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "toUser.name", "去向", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 400, false);
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
			formField = addFormFieldRefrence(form, "formUser.name", "来自", groupName, "CRM_Employee", false, true);
			formField = addFormFieldRefrence(form, "toUser.name", "去向", groupName, "CRM_Employee", false, true);
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
	
	public void addInspectionLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(inspectionDetailResourceNodeCode);

		PDatagrid datagrid = new PDatagrid(node, "抽查日志");
		{
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			// 子页面枚举显示需要格式化一下
			PDatagridColumn column = addColumn(datagrid, "inspectionType", "抽查类型", ControlTypes.ENUM_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(TaskInspectionType.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "inspectionState", "抽查异常状态", ControlTypes.ENUM_BOX, 150);
			{
				String formatter = EnumUtil.getColumnFormatter(TaskInspectionState.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 400);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("抽查日志");
			PFormField formField = null;
			String groupName = null;
			formField = addFormField(form, "inspectionType", "抽查类型", groupName, ControlTypes.ENUM_BOX, false, true);
			formField = addFormField(form, "inspectionState", "抽查异常状态", groupName, ControlTypes.ENUM_BOX, false, true);
			formField = addFormField(form, "content", "内容", groupName, ControlTypes.TEXT_BOX, false, true);
			{
				formField.setFullColumn(true);
			}
		}
		
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("抽查日志");
			part.setCode("inspections");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("inspections");
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
