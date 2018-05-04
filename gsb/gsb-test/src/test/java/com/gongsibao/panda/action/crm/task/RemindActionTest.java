package com.gongsibao.panda.action.crm.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.remind.ActionRemindPersist;
import com.gongsibao.crm.service.action.task.remind.ActionRemindRecordLog;
import com.gongsibao.crm.service.action.task.remind.ActionRemindVerify;
import com.gongsibao.crm.service.action.task.remind.ActionRemindWriteBack;
import com.gongsibao.panda.action.BaseActionTest;

/**
 * 提醒
 * @author Administrator
 *
 */
public class RemindActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/remind";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("商机提醒");
		}

		createBean(beanPath, "验证", ActionRemindVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionRemindPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionRemindWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionRemindRecordLog.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
