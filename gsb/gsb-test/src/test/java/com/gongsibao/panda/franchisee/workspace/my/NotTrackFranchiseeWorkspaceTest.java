package com.gongsibao.panda.franchisee.workspace.my;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.Franchisee;

public class NotTrackFranchiseeWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/bd/franchisee/my/nottrack/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "BD_MY_NOT_TRACK";
		listFilter = "ownerId='{userId}' and (last_tracker_id is null or owner_id <>last_tracker_id)";
	}
	
	@Test
	public void run() {
		createListWorkspace();
	}
}
