package com.gongsibao.panda.supplier.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.release.ActionReleasePersist;
import com.gongsibao.crm.service.action.task.release.ActionReleaseRecordLog;
import com.gongsibao.crm.service.action.task.release.ActionReleaseVerify;
import com.gongsibao.crm.service.action.task.release.ActionReleaseWriteBack;
import com.gongsibao.panda.supplier.crm.action.BaseActionTest;

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
			beanPath.setName("商机释放");
		}

		createBean(beanPath, "验证", ActionReleaseVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionReleasePersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionReleaseWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionReleaseRecordLog.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
