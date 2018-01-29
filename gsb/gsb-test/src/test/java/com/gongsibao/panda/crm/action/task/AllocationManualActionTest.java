package com.gongsibao.panda.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.allocation.manual.ActionManualAllocationPersist;
import com.gongsibao.crm.service.action.task.allocation.manual.ActionManualAllocationRecordLog;
import com.gongsibao.crm.service.action.task.allocation.manual.ActionManualAllocationSendMessage;
import com.gongsibao.crm.service.action.task.allocation.manual.ActionManualAllocationVerify;
import com.gongsibao.crm.service.action.task.allocation.manual.ActionManualAllocationWriteBack;
import com.gongsibao.panda.crm.action.BaseActionTest;

public class AllocationManualActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/task/allocation/manual";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务手动分配");
		}

		createBean(beanPath, "验证", ActionManualAllocationVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionManualAllocationPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionManualAllocationWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionManualAllocationRecordLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionManualAllocationSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
