package com.gongsibao.er;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.er.workspace.config.CustomerWorkspaceTest;
import com.gongsibao.er.workspace.config.FlowWorkspaceTest;
import com.gongsibao.er.workspace.config.GoodsWorkspaceTest;
import com.gongsibao.er.workspace.config.TaskWorkspaceTest;
import com.gongsibao.er.workspace.config.TemplateWorkspaceTest;
import com.gongsibao.er.workspace.task.MyTaskWorkspaceTest;
import com.gongsibao.er.workspace.task.OrderWorkspaceTest;
import com.gongsibao.er.workspace.task.ReportWorkspaceTest;
import com.gongsibao.er.workspace.task.UnAduitWorkspaceTest;

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