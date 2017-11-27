package com.gongsibao.franchisee.workspace.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.Franchisee;


public class UnTrackFranchiseeWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {

		urlList = "/bd/franchisee/my/untrack/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "BD_MY_UN_TRACK";
		listFilter = "ownerId='{userId}' and nextTrackDate<now()";
	}
}
