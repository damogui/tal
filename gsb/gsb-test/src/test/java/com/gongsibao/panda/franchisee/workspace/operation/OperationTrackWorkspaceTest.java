package com.gongsibao.panda.franchisee.workspace.operation;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.panda.franchisee.workspace.department.DepartmentTrackWorkspaceTest;

public class OperationTrackWorkspaceTest  extends DepartmentTrackWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/bd/operation/franchisee/track/list";
		urlForm = "/bd/department/franchisee/track/form";
		entity = FranchiseeTrack.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "跟进信息";
		resourceNodeCode = "GSB_BD_OPERATION_Track";
		listFilter = null;
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.setColumnCount(4);
		PQueryItem queryItem = null;
		addQueryItem(queryProject, "lastTracker.name", "最后跟进人", ControlTypes.TEXT_BOX);
		queryItem = addRefrenceQueryItem(queryProject, "franchisee.department.name", "所属部门", "BD-Organization-Department-Filter");
		{
			queryItem.setRefFilter("organizationType=" + OrganizationType.DEPARTMENT.getValue());
		}
		queryItem = addRefrenceQueryItem(queryProject, "franchisee.owner.name", "业务员", Employee.class.getSimpleName());
		addQueryItem(queryProject, "id", "客户Id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
}
