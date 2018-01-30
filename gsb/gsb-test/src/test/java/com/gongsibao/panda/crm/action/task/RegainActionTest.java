package com.gongsibao.panda.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.regain.ActionRegainPersist;
import com.gongsibao.crm.service.action.task.regain.ActionRegainRecordLog;
import com.gongsibao.crm.service.action.task.regain.ActionRegainVerify;
import com.gongsibao.crm.service.action.task.regain.ActionRegainWriteBack;
import com.gongsibao.panda.crm.action.BaseActionTest;

public class RegainActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/regain";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务收回");
		}

		createBean(beanPath, "验证", ActionRegainVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionRegainPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionRegainWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionRegainRecordLog.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
