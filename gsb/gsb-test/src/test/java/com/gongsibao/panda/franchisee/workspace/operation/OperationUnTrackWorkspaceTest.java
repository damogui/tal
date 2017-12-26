package com.gongsibao.panda.franchisee.workspace.operation;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

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
		listFilter = "owner_id<>last_tracker_id or (last_tracker_id is null and owner_id is not null)";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
}
