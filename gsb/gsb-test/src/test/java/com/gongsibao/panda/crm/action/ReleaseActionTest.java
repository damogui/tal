package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.release.ActionReleaseSave;
import com.gongsibao.crm.service.action.task.release.ActionReleaseSaveLog;
import com.gongsibao.crm.service.action.task.release.ActionReleaseSendMessage;
import com.gongsibao.crm.service.action.task.release.ActionReleaseVerify;
import com.gongsibao.crm.service.action.task.release.ActionReleaseWriteBack;

public class ReleaseActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/release";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务释放");
		}

		createBean(beanPath, "验证", ActionReleaseVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionReleaseSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionReleaseWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionReleaseSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionReleaseSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
