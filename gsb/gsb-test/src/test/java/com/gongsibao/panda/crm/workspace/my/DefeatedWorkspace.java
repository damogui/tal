package com.gongsibao.panda.crm.workspace.my;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
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

import com.gongsibao.crm.web.MyAllTaskListPart;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskInspection;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.TaskInspectionType;
import com.gongsibao.panda.operation.workspace.crm.form.TaskEditWorkspaceTest;

public class DefeatedWorkspace extends TaskEditWorkspaceTest{

	@Override
	@Before
	public void setup() {
		entity = NCustomerTask.class;
		//配置表单路径
		urlForm = "/crm/my/task/defeated/from";	
		//配置资源路径
		urlList = "/crm/my/task/defeated/list";
		listPartName = formPartName = "无法签单";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_MY_TASK_DEFEATED";
		listFilter = "foolowStatus = 4 and ownerId = '{userId}'";
		
		//扩展列表操作
		//listToolbarPath = "crm/my/task/all/toolbar";
		listPartImportJs ="/gsb/crm/js/crm-inspectionTask-list.js";
		listPartJsController = MyAllTaskListPart.class.getName();
		listPartServiceController = MyAllTaskListPart.class.getName();
		
		//重写加载项code
		productsDetailResourceNodeCode = "GSB_CRM_My_Manager_Products";
		foolowDetailResourceNodeCode = NCustomerTaskFoolow.class.getSimpleName();
		notifyDetailResourceNodeCode = NCustomerTaskNotify.class.getSimpleName();
		changeDetailResourceNodeCode = NCustomerChange.class.getSimpleName();
	}
	
	@Test
	public void run() {
		createListWorkspace();
		createFormWorkspace();
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		datagrid.setShowCheckbox(true);
		//添加行的toolbar
		datagrid.setToolbar("crm/my/task/all/row/toolbar");
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "department.name", "分配服务商部门", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "name", "任务名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.id", "客户ID", ControlTypes.NUMBER_BOX, 100, false);
		addColumn(datagrid, "customer.isMember", "是否会员", ControlTypes.BOOLCOMBO_BOX, 100, false);
		addColumn(datagrid, "customer.realName", "联系人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "customer.mobile", "手机号", ControlTypes.TEXT_BOX, 100, false);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		addColumn(datagrid, "customer.telephone", "座机", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.qq", "QQ", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.weixin", "微信", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "intentionCategory", "客户质量分类", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "source.name", "任务来源", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "ownerId", "业务员Id", ControlTypes.TEXT_BOX, 100, false);{
			column.setSystem(true);
			column.setVisible(false);
		}
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 100, false);
		//任务抽查状态为3、4 抽查状态都为抽查异常；1、2对应枚举状态
		addColumn(datagrid, "inspectionState", "抽查状态", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "lastInspectionUser.name", "最后抽查人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastInspectionTime", "最后抽查时间", ControlTypes.DATE_BOX, 100, false);
		//任务抽查状态为1、2、3 处理状态都为未处理；4是已处理
		column = addColumn(datagrid, "processingState", "处理状态", ControlTypes.NUMBER_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("if(value == 1 || value == 2 || value == 3){ return '未处理' } else{ return '已处理' }");
		}
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
	
	// 创建选项卡
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		addCommunicatLogsPart(workspace);
		createProductsPart(workspace);
		addNotificationLogPart(workspace);
		addFlowLogPart(workspace);
		addInspectionLogPart(workspace);
	}	
	public void addInspectionLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(NCustomerTaskInspection.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "抽查日志");
		{
			// 子页面枚举显示需要格式化一下
			PDatagridColumn column = addColumn(datagrid, "inspectionType", "类型", ControlTypes.ENUM_BOX, 300);
			{
				String formatter = EnumUtil.getColumnFormatter(TaskInspectionType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "creator", "操作人", ControlTypes.ENUM_BOX, 150);
			addColumn(datagrid, "createTime", "操作时间", ControlTypes.DATE_BOX, 150);
			addColumn(datagrid, "content", "记录内容", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("抽查日志");
			PFormField formField = null;
			String groupName = null;
			formField = addFormField(form, "inspectionType", "类型", groupName, ControlTypes.ENUM_BOX, false, true);
			formField = addFormFieldRefrence(form, "creator", "操作人", groupName, "CRM_Employee", false, true);
			formField = addFormField(form, "createTime", "最后分配人", groupName, ControlTypes.DATE_BOX, false, true);
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
	
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		//operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}		
}
