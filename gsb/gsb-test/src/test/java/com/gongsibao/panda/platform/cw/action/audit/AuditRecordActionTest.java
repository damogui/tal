package com.gongsibao.panda.platform.cw.action.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.cw.service.action.audit.ActionAuditRecordAudit;
import com.gongsibao.cw.service.action.audit.ActionAuditRecordLog;
import com.gongsibao.cw.service.action.audit.ActionAuditRecordPersist;
import com.gongsibao.cw.service.action.audit.ActionAuditRecordSendMessage;
import com.gongsibao.cw.service.action.audit.ActionAuditRecordVerify;
import com.gongsibao.panda.action.BaseActionTest;


public class AuditRecordActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "GSB_CW_Manage_Audit_Record";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/cw/audit/form";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("审批记录");
		}
		createBean(beanPath, "1.验证", ActionAuditRecordVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionAuditRecordPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionAuditRecordAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionAuditRecordSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionAuditRecordLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
