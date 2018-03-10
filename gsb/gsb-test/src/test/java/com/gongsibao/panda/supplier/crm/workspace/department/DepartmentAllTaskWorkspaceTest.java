package com.gongsibao.panda.supplier.crm.workspace.department;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.DepartmentAllTaskListPart;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.panda.operation.workspace.crm.TaskALLWorkspaceTest;

public class DepartmentAllTaskWorkspaceTest extends TaskALLWorkspaceTest{

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/crm/department/task/all/list";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_ALL";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/supplier/crm/base/js/task-base-list.part.js");
		ss.add("/gsb/supplier/crm/department/js/task-all-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		ss.add("/gsb/supplier/crm/base/js/task-follow-base.ctrl.js");
		listPartImportJs = StringManager.join("|", ss);

		//当前登录人所在部门的子部门,需要扩展
		
		listPartJsController = DepartmentAllTaskListPart.class.getName();
		listPartServiceController = DepartmentAllTaskListPart.class.getName();
		
		listFilter = null;
		
		listToolbarPath = "department/task/all";
		rowToolbaPath = "department/task/all/row";
	}

	@Override
	public PToolbar createListToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("部门任务工具栏操作");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "add", "新增任务", "fa fa-plus", "add()", null, 5);
		addToolbarItem(toolbar, "regain", "收回", "fa fa-mail-reply", "regain()", null, 6);
		addToolbarItem(toolbar, "batchTransfer", "任务转移", "fa fa-share-square-o", "batchTransfer()", null, 7);
		return toolbar;
	}

	@Override
	public PToolbar createRowToolbar() {

		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(rowToolbaPath);
			toolbar.setName("跟进行工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "follow", "跟进", "fa fa-edit", "follow()", null, 6);
		return toolbar;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		queryProject.toNew();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(3);
		queryProject.setName(listPartName);
		PQueryItem item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
		{
			item.setTooltip("输入客户ID、客户名称、联系方式等");
			item.setWidth(250);
		}
		item = addQueryItem(queryProject, "source.name", "任务来源", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=411");
		}
		addRefrenceQueryItem(queryProject, "supplier.name", "费用部门", Supplier.class.getSimpleName());
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		
		addRefrenceQueryItem(queryProject, "quality.name", "客户质量", NCustomerTaskQuality.class.getSimpleName());

		//意向产品
		addQueryItem(queryProject, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX);
		
		//未跟进天数
		addQueryItem(queryProject, "lastFollowTime", "最后跟进时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
	
}
