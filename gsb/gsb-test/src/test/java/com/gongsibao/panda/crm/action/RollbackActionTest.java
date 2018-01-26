package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.rollback.ActionRollbackSave;
import com.gongsibao.crm.service.action.rollback.ActionRollbackSaveLog;
import com.gongsibao.crm.service.action.rollback.ActionRollbackSendMessage;
import com.gongsibao.crm.service.action.rollback.ActionRollbackVerify;
import com.gongsibao.crm.service.action.rollback.ActionRollbackWriteBack;

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
		createBean(beanPath, "保存", ActionRollbackSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionRollbackWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionRollbackSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionRollbackSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
