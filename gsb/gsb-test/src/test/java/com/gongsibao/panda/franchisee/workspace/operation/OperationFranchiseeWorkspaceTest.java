package com.gongsibao.panda.franchisee.workspace.operation;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.panda.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

public class OperationFranchiseeWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/bd/operation/franchisee/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "GSB_BD_OPERATION_Franchisee";
		listFilter = null;
		listToolbarPath = "/bd/operation/franchisee/edit";
	}
	
	
	@Test
	public void run() {
		createListWorkspace();
	}
	
	@Test
	public void listPartToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("客户池列表工具栏");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("allot");
			item.setIcon("fa fa-user-plus fa-fw");
			item.setName("分配");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4);
			item.setCommand("{controller}.allot();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setSingleSelect(false);
		datagrid.setShowCheckbox(true);
		
		PDatagridColumn column = addColumn(datagrid, "allotStatus", "状态", ControlTypes.ENUM_BOX, 60, true);{
			
			column.setStyler("return row.allotStatus=='已分配'?'color:blue;':'color:red;';");
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		addQueryItem(queryProject, "lastTracker.name", "最后跟进人", ControlTypes.TEXT_BOX);
		PQueryItem queryItem = addRefrenceQueryItem(queryProject, "department.name", "所属部门", "BD-Organization-Department-Filter");{
			queryItem.setRefFilter("organizationType="+OrganizationType.DEPARTMENT.getValue());
		}
		addQueryItem(queryProject, "allotStatus", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
}
