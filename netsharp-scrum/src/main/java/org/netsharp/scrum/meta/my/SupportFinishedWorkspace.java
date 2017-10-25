package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.meta.story.SupportWorkspaceTest;

public class SupportFinishedWorkspace extends SupportWorkspaceTest {
	
	@Override
	@Before
	public void setup() {
		
		// 在子类中重定义
		urlList = "/scrum/my/support/finished/list";
		urlForm = "/scrum/my/support/finished/form";

		entity = Support.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="my-support-finished";
		listPartName =formPartName= "已完成支持";
		
		listFilter = "status not in (1,3) and ownerId = '{userId}'";
	}
}
