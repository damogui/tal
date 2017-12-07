package com.gongsibao.panda.franchisee.workspace.my;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.Franchisee;


public class UnTrackFranchiseeWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/bd/franchisee/my/untrack/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "供应商信息";
		resourceNodeCode = "BD_MY_UN_TRACK";
		listFilter = "ownerId='{userId}' and TO_DAYS(next_track_date) - TO_DAYS(NOW())>=7";
	}
	
	@Test
	public void run() {
		createListWorkspace();
	}
}
