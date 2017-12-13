package com.gongsibao.franchisee.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

public class DepartmentUnTrackWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		urlList = "/bd/department/franchisee/untrack/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "BD_DEPARTMENT_Franchisee_UnTrack";
		listFilter = "organizationId='{departmentId}' and nextTrackDate<now()";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
}
