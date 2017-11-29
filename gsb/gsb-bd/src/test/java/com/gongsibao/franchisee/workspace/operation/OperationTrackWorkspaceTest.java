package com.gongsibao.franchisee.workspace.operation;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.franchisee.workspace.department.DepartmentTrackWorkspaceTest;

public class OperationTrackWorkspaceTest  extends DepartmentTrackWorkspaceTest{

	@Override
	@Before
	public void setup() {

		urlList = "/bd/operation/franchisee/track/list";
		urlForm = "/bd/operation/franchisee/track/form";
		entity = FranchiseeTrack.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "跟进信息";
		resourceNodeCode = "GSB_BD_OPERATION_Track";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
}
