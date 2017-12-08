package com.gongsibao.panda.iduty;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.iduty.workspace.config.CustomerWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.config.FlowWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.config.GoodsWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.config.TaskWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.config.TemplateWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.task.MyTaskWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.task.OrderWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.task.ReportWorkspaceTest;
import com.gongsibao.panda.iduty.workspace.task.UnAduitWorkspaceTest;




@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	//任务管理
	ReportWorkspaceTest.class,
	OrderWorkspaceTest.class,
	MyTaskWorkspaceTest.class,
	UnAduitWorkspaceTest.class,
	
	//配置管理
	FlowWorkspaceTest.class,
	TemplateWorkspaceTest.class,
	CustomerWorkspaceTest.class,
	GoodsWorkspaceTest.class,
	TaskWorkspaceTest.class,
	
	NavigationTest.class
	
})

public class AllTest {

}


