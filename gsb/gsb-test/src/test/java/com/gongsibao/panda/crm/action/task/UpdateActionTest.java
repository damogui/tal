package com.gongsibao.panda.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.update.ActionUpdateSave;
import com.gongsibao.crm.service.action.task.update.ActionUpdateSaveLog;
import com.gongsibao.crm.service.action.task.update.ActionUpdateSendMessage;
import com.gongsibao.crm.service.action.task.update.ActionUpdateVerify;
import com.gongsibao.crm.service.action.task.update.ActionUpdateWriteBack;
import com.gongsibao.panda.crm.action.BaseActionTest;

public class UpdateActionTest extends BaseActionTest {
	
	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/update";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务修改");
		}

		createBean(beanPath, "验证", ActionUpdateVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionUpdateSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionUpdateWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionUpdateSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionUpdateSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
