package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.add.ActionAddSave;
import com.gongsibao.crm.service.action.task.add.ActionAddSaveLog;
import com.gongsibao.crm.service.action.task.add.ActionAddSendMessage;
import com.gongsibao.crm.service.action.task.add.ActionAddVerify;
import com.gongsibao.crm.service.action.task.add.ActionAddWriteBack;

public class AddActionTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/add";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务新增");
		}

		createBean(beanPath, "验证", ActionAddVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionAddSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionAddWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionAddSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionAddSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
