package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.scrum.entity.Bug;
import org.netsharp.scrum.meta.bug.BugWorkspaceTest;

public class MyBugWorkspaceTest extends BugWorkspaceTest {
	
	@Override
	@Before
	public void setup() {
		
		// 在子类中重定义
		urlList = "/scrum/my/bug/list";
		urlForm = "/scrum/my/bug/form";

		entity = Bug.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="bug-devp-my";
		listPartName =formPartName= "我的缺陷";
		this.listFilter="developerId = '{userId}'";
	}
}
