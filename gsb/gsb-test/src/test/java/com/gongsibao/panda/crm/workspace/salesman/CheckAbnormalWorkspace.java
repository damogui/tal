package com.gongsibao.panda.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
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
import com.gongsibao.crm.web.NCustomerFollowPart;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.crm.dic.QualityCategory;

public class CheckAbnormalWorkspace extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = NCustomerTask.class;
		//配置资源路径
		urlList = "/crm/salesman/check/abnormal/list";
			
		listPartName = formPartName = "抽查异常";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_CHECK_ABNORMAL";
		//无法签单并且抽查状态等于正常
		listFilter = "inspection_state = 2 and foolowStatus=4 and ownerId = '{userId}'";
		
		//扩展列表操作
		listToolbarPath = "crm/my/task/all/toolbar";
		listPartImportJs ="/gsb/crm/js/crm-allTask-list.js";
		listPartJsController = MyAllTaskListPart.class.getName();
		listPartServiceController = MyAllTaskListPart.class.getName();		
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
		
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}		
}