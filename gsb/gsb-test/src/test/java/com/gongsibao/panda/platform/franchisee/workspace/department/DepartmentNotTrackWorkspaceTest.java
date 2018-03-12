package com.gongsibao.panda.platform.franchisee.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.panda.platform.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

public class DepartmentNotTrackWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		urlList = "/bd/department/franchisee/nottrack/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "BD_DEPARTMENT_Franchisee_NotTrack";
		listFilter = "department_id in ({departments}) and (owner_id<>last_tracker_id or (last_tracker_id is null and owner_id is not null))";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		PQueryItem queryItem = addRefrenceQueryItem(queryProject, "department.name", "所属部门", "BD-Organization-Department-Filter");{
			queryItem.setRefFilter("organizationType="+OrganizationType.DEPARTMENT.getValue());
		}
		queryItem = addRefrenceQueryItem(queryProject, "owner.name", "业务员", Employee.class.getSimpleName());
		addQueryItem(queryProject, "allotStatus", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
}
