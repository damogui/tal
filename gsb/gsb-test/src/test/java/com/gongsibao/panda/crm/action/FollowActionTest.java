package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.follow.ActionFollowSave;
import com.gongsibao.crm.service.action.follow.ActionFollowSendMessage;
import com.gongsibao.crm.service.action.follow.ActionFollowVerify;
import com.gongsibao.crm.service.action.follow.ActionFollowWriteBack;

public class FollowActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/follow";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务跟进");
		}

		createBean(beanPath, "验证", ActionFollowVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionFollowSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionFollowWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "通知", ActionFollowSendMessage.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
