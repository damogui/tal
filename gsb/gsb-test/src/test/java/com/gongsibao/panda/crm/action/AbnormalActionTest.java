package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.abnormal.ActionAbnormalSave;
import com.gongsibao.crm.service.action.abnormal.ActionAbnormalSaveLog;
import com.gongsibao.crm.service.action.abnormal.ActionAbnormalSendMessage;
import com.gongsibao.crm.service.action.abnormal.ActionAbnormalVerify;
import com.gongsibao.crm.service.action.abnormal.ActionAbnormalWriteBack;

/**
 * @author hw
 *
 */
public class AbnormalActionTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "GSB_CRM_MY_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/abnormal";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("抽查异常");
		}

		createBean(beanPath, "验证", ActionAbnormalVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionAbnormalSave.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionAbnormalWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionAbnormalSaveLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionAbnormalSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
