package com.gongsibao.panda.action.crm.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.save.ActionSaveRecordLog;
import com.gongsibao.crm.service.action.task.save.ActionSaveTaskAllocation;
import com.gongsibao.crm.service.action.task.save.ActionSaveTaskPersist;
import com.gongsibao.crm.service.action.task.save.ActionSaveTaskVerify;
import com.gongsibao.crm.service.action.task.save.ActionSaveWriteBack;
import com.gongsibao.panda.action.BaseActionTest;

public class SaveActionTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/save";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("商机新增");
		}

		createBean(beanPath, "验证", ActionSaveTaskVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionSaveTaskPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "日志", ActionSaveRecordLog.class.getName(), resourceNode, 300);
		createBean(beanPath, "回写", ActionSaveWriteBack.class.getName(), resourceNode, 400);
		createBean(beanPath, "分配", ActionSaveTaskAllocation.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
