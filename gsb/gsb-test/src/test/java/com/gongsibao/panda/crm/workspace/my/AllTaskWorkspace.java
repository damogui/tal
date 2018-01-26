package com.gongsibao.panda.crm.workspace.my;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
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
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.MyAllTaskListPart;
import com.gongsibao.crm.web.NCustomerFollowPart;
import com.gongsibao.crm.web.NCustomerTaskProductDetailPart;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.crm.dic.QualityCategory;

public class AllTaskWorkspace extends WorkspaceCreationBase{

	//行的toolbar
	String listRowToolbarPath=null;
	@Override
	@Before
	public void setup() {
			
		entity = NCustomerTask.class;
		//配置资源路径
		urlList = "/crm/salesman/task/all/list";
		
		listPartName = formPartName = "全部任务";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		listFilter = "ownerId = '{userId}'";
		
		//行的toolbar
		listRowToolbarPath = "crm/my/task/all/row/toolbar";
		//扩展列表操作
		listToolbarPath = "crm/my/task/all/toolbar";
		listPartImportJs ="/gsb/crm/js/crm-allTask-list.js";
		listPartJsController = MyAllTaskListPart.class.getName();
		listPartServiceController = MyAllTaskListPart.class.getName();
	}

	//列表Toolbar
	@Test
	public void createListToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("开通会员工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "openMemberPopup", "开通会员", "fa fa-edit", "openMemberPopup()", null, 5);
		addToolbarItem(toolbar, "taskTransferPopup", "任务转移", "fa fa-edit", "taskTransferPopup()", null, 6);
		toolbarService.save(toolbar);
	}
	@Test
	public void createRowToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listRowToolbarPath);
			toolbar.setName("跟进行工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "followUpPopup", "跟进", "fa fa-edit", "followUpPopup()", null, 6);
		addToolbarItem(toolbar, "backTaskPopup", "释放", "fa fa-edit", "backTaskPopup()", null, 7);
		toolbarService.save(toolbar);
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		datagrid.setShowCheckbox(true);
		//添加行的toolbar
		datagrid.setToolbar(listRowToolbarPath);
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
