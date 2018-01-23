package com.gongsibao.panda.gardian;
import com.gongsibao.panda.gardian.workspace.DeviceWorkspaceTest;
import com.gongsibao.panda.gardian.workspace.ProvidesWorkspaceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class,
		DeviceWorkspaceTest.class,
		ProvidesWorkspaceTest.class,
		NavigationTest.class
		})
		
public class AllTest {

}