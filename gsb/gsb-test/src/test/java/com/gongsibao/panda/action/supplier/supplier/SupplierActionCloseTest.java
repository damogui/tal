package com.gongsibao.panda.action.supplier.supplier;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.supplier.action.supplier.close.ActionSupplierDisableAccount;
import com.gongsibao.supplier.action.supplier.close.ActionSupplierVerifyStatus;

public class SupplierActionCloseTest  extends BaseActionTest {
	@Before
	public void setup() {

		resourceNodeCode = "GSB_Operation_Supplier";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/operation/supplier/account/close";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("服务商销户");
		}

		createBean(beanPath, "状态验证", ActionSupplierVerifyStatus.class.getName(), resourceNode, 100);
		createBean(beanPath, "停用所有帐号", ActionSupplierDisableAccount.class.getName(), resourceNode, 200);
		beanPathService.save(beanPath);
	}
}
