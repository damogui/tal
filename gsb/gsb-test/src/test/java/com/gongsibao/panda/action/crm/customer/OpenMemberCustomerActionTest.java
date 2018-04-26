package com.gongsibao.panda.action.crm.customer;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.customer.member.ActionMemberPersist;
import com.gongsibao.crm.service.action.customer.member.ActionMemberRecordLog;
import com.gongsibao.crm.service.action.customer.member.ActionMemberSendMessage;
import com.gongsibao.crm.service.action.customer.member.ActionMemberVerify;
import com.gongsibao.crm.service.action.customer.member.ActionMemberWriteBack;
import com.gongsibao.panda.action.BaseActionTest;

public class OpenMemberCustomerActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/customer/member";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("开通会员");
		}

		createBean(beanPath, "验证", ActionMemberVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionMemberPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionMemberWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionMemberRecordLog.class.getName(), resourceNode, 400);
		createBean(beanPath, "通知", ActionMemberSendMessage.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
