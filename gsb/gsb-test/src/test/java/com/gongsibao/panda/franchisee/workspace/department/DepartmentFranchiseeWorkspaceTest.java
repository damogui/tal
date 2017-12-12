package com.gongsibao.panda.franchisee.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.panda.franchisee.workspace.my.MyFranchiseeWorkspaceTest;

public class DepartmentFranchiseeWorkspaceTest  extends MyFranchiseeWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/bd/department/franchisee/list";
		urlForm = "/bd/franchisee/my/form";
		entity = Franchisee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "供应商信息";
		resourceNodeCode = "BD_DEPARTMENT_Franchisee";
		listFilter = "departmentId='{departmentId}'";
	}
	
	@Test
	public void run() {

		createListWorkspace();
	}
}
