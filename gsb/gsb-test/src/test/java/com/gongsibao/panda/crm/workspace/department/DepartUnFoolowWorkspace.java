package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;

public class DepartUnFoolowWorkspace extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = NCustomerTask.class;
		//配置资源路径
		urlList = "/crm/department/4/list";
		//配置表单路径
		urlForm = "/crm/department/4/from";		
		listPartName = formPartName = "待跟进";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_DEPARTMENT_UNFOOLOW";
		listFilter = "foolowStatus = 2 and creator_id = '{userId}'";
	}
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "customer.realName", "客户", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "department.name", "分配服务商部门", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "old", "是否老客户", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 100, false);
		return datagrid;
	}
	//配置查询条件
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "customer.realName", "客户", ControlTypes.TEXT_BOX);{
			item.setRequired(true);
		}
		return queryProject;
	}
	
	//创建选项卡
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		addCommunicatLogsPart(workspace);
		addNotificationLogPart(workspace);
		addFlowLogPart(workspace);
	}	
	private void addCommunicatLogsPart(PWorkspace workspace) {
		//需要配置资源
		ResourceNode node = this.resourceService.byCode(NCustomerTaskFoolow.class.getSimpleName());
		
		PDatagrid datagrid = new PDatagrid(node, "沟通日志");
		{
			PDatagridColumn column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 300);{
				String formatter = EnumUtil.getColumnFormatter(CustomerFollowStatus.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 150);
			addColumn(datagrid, "estimateAmount", "估计签单金额", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("沟通日志");
			PFormField formField = null;
			String groupName = null;
			
			formField = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, false);
			addFormField(form, "estimateAmount", "估计签单金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
			formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
				formField.setFullColumn(true);
		    }
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
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}	
	private void addNotificationLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(NCustomerTaskNotify.class.getSimpleName());
		
		PDatagrid datagrid = new PDatagrid(node, "通知日志");
		{
			PDatagridColumn column = addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 300);{
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
			
			formField = addFormField(form, "type", "通知类型", groupName, ControlTypes.ENUM_BOX, false, false);
			formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
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
			//part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}	
	private void addFlowLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(NCustomerChange.class.getSimpleName());
		
		PDatagrid datagrid = new PDatagrid(node, "流转日志");
		{
			//子页面枚举显示需要格式化一下
			PDatagridColumn column = addColumn(datagrid, "changeType", "流转类型", ControlTypes.ENUM_BOX, 300);{
				String formatter = EnumUtil.getColumnFormatter(ChangeType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "formUser.name", "来自", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "toUser.name", "去向", ControlTypes.TEXT_BOX, 150);
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
			formField = addFormField(form, "changeType", "流转类型", groupName, ControlTypes.ENUM_BOX, false, false);
			formField = addFormFieldRefrence(form, "formUser.name", "最后分配人", groupName, "CRM_Employee", false, false);
			formField = addFormFieldRefrence(form, "toUser.name", "最后分配人", groupName, "CRM_Employee", false, false);
			formField = addFormField(form, "content", "内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
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
	//创建表单。须配置urlForm路径
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}
		PFormField formField = null;
		String groupName = null;
		
		addFormField(form, "name", "名称", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormFieldRefrence(form, "supplier.name", "分配服务商", groupName, "CRM_Supplier", false, true);
		addFormFieldRefrence(form, "department.name", "分配服务商部门", groupName, "CRM_Supplier_Depart", false, true);
		
		formField = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "intentionCategory", "质量分类", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "old", "是否老客户", groupName, ControlTypes.SWITCH_BUTTON, false, true);
		
		
		addFormFieldRefrence(form, "customer.realName", "客户", groupName, "CRM_NCustomer", false, true);
		addFormFieldRefrence(form, "lastAllocationUser.name", "最后分配人", groupName, "CRM_Employee", false, true);
		addFormFieldRefrence(form, "lastFoolowUser.name", "最后跟进人", groupName, "CRM_Employee", false, true);
		
		addFormField(form, "lastFollowTime", "最近跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		
		formField = addFormField(form, "lastContent", "最后跟进内容", groupName, ControlTypes.TEXT_BOX, false, true);{			
			formField.setFullColumn(true);
	    }
		
		formField = addFormField(form, "memoto", "备注", groupName, ControlTypes.TEXT_BOX, false, true);{			
			formField.setFullColumn(true);
	    }
		return form;
	}	
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}		
}
