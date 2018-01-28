package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.allocation.ActionAllocationSave;
import com.gongsibao.crm.service.action.task.allocation.ActionAllocationSaveLog;
import com.gongsibao.crm.service.action.task.allocation.ActionAllocationSendMessage;
import com.gongsibao.crm.service.action.task.allocation.ActionAllocationVerify;
import com.gongsibao.crm.service.action.task.allocation.ActionAllocationWriteBack;

public class AllocationActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/allocation";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务分配");
		}

		createBean(beanPath, "验证", ActionAllocationVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionAllocationSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionAllocationWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionAllocationSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionAllocationSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
