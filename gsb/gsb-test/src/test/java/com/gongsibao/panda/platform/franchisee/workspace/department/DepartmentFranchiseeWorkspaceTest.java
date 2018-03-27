package com.gongsibao.panda.platform.franchisee.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.franchisee.web.FranchiseeFormPart;
import com.gongsibao.panda.platform.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

public class DepartmentFranchiseeWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/bd/department/franchisee/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "BD_DEPARTMENT_Franchisee";
		listFilter = "department_id in ({departments})";
		listToolbarPath = "/bd/operation/franchisee/edit";
		formServiceController =FranchiseeFormPart.class.getName();
	}
	
	@Test
	public void run() {

		createListWorkspace();
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
		queryItem = addRefrenceQueryItem(queryProject, "owner.name", "业务员", Employee.class.getSimpleName());
		addQueryItem(queryProject, "allotStatus", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "province.name", "省份", ControlTypes.TEXT_BOX);
		return queryProject;
	}
}
