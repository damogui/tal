package com.gongsibao.panda.franchisee.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.panda.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

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
		listFilter = "department_id in ({departments}) and (owner_id<>last_tracker_id or (last_tracker_id is null and owner_id is not null))";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
}
