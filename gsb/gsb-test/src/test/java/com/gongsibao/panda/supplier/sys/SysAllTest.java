package com.gongsibao.panda.supplier.sys;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.sys.workspace.SysDepartmentWorkspaceTest;
import com.gongsibao.panda.supplier.sys.workspace.SysSalesmanWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	SysResourceTest.class,
	
	//系统设置
	SysDepartmentWorkspaceTest.class,
	SysSalesmanWorkspaceTest.class,

	SysNavigationTest.class, 
})
public class SysAllTest {

}
