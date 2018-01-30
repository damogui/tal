package com.gongsibao.panda.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.rollback.ActionRollbackPersist;
import com.gongsibao.crm.service.action.task.rollback.ActionRollbackRecordLog;
import com.gongsibao.crm.service.action.task.rollback.ActionRollbackVerify;
import com.gongsibao.crm.service.action.task.rollback.ActionRollbackWriteBack;
import com.gongsibao.panda.crm.action.BaseActionTest;

public class RollbackActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/rollback";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务回退");
		}

		createBean(beanPath, "验证", ActionRollbackVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionRollbackPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionRollbackWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionRollbackRecordLog.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
