package com.gongsibao.panda.platform.cw.action.allocation;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.cw.service.action.allocation.ActionAllocationApplyAudit;
import com.gongsibao.cw.service.action.allocation.ActionAllocationApplyLog;
import com.gongsibao.cw.service.action.allocation.ActionAllocationApplyPersist;
import com.gongsibao.cw.service.action.allocation.ActionAllocationApplySendMessage;
import com.gongsibao.cw.service.action.allocation.ActionAllocationApplyVerify;
import com.gongsibao.panda.action.BaseActionTest;


public class AllocationBillActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "GSB_CW_Manage_Allocation_Bill";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/cw/allocation/form";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("调拨申请");
		}
		createBean(beanPath, "1.验证", ActionAllocationApplyVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionAllocationApplyPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionAllocationApplyAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionAllocationApplySendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionAllocationApplyLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
