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

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.panda.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

public class OperationUnTrackWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		urlList = "/bd/operation/franchisee/untrack/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "供应商信息";
		resourceNodeCode = "GSB_BD_OPERATION_UnTrack";
		listFilter = "TO_DAYS(next_track_date) - TO_DAYS(NOW())<=7";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.setColumnCount(4);
		addQueryItem(queryProject, "lastTracker.name", "最后跟进人", ControlTypes.TEXT_BOX);
		PQueryItem queryItem = addRefrenceQueryItem(queryProject, "department.name", "所属部门", "BD-Organization-Department-Filter");
		{
			queryItem.setRefFilter("organizationType=" + OrganizationType.DEPARTMENT.getValue());
		}
		queryItem = addRefrenceQueryItem(queryProject, "owner.name", "业务员", Employee.class.getSimpleName());
		addQueryItem(queryProject, "allotStatus", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "id", "客户Id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
}
