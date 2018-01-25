package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.transfer.ActionTransferSave;
import com.gongsibao.crm.service.action.transfer.ActionTransferSaveLog;
import com.gongsibao.crm.service.action.transfer.ActionTransferSendMessage;
import com.gongsibao.crm.service.action.transfer.ActionTransferVerify;
import com.gongsibao.crm.service.action.transfer.ActionTransferWriteBack;

public class TransferActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "GSB_CRM_MY_CUSTOMER";
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
			beanPath.setName("任务转移");
		}

		createBean(beanPath, "验证", ActionTransferVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionTransferSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionTransferWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionTransferSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionTransferSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
