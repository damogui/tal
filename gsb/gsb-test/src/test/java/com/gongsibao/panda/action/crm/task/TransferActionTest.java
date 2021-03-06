package com.gongsibao.panda.action.crm.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.transfer.ActionTransferPersist;
import com.gongsibao.crm.service.action.task.transfer.ActionTransferRecordLog;
import com.gongsibao.crm.service.action.task.transfer.ActionTransferVerify;
import com.gongsibao.crm.service.action.task.transfer.ActionTransferWriteBack;
import com.gongsibao.panda.action.BaseActionTest;

public class TransferActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/transfer";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("商机转移");
		}

		createBean(beanPath, "验证", ActionTransferVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionTransferPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionTransferWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionTransferRecordLog.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
