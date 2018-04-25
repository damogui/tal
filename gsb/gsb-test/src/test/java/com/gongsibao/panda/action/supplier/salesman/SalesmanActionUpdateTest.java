package com.gongsibao.panda.action.supplier.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.supplier.action.salesman.update.ActionUpdateSalesmanPersist;
import com.gongsibao.supplier.action.salesman.update.ActionUpdateSalesmanVerify;
import com.gongsibao.supplier.action.salesman.update.ActionUpdateSalesmanWriteBack;

public class SalesmanActionUpdateTest extends BaseActionTest{
	
	@Before
	public void setup() {

		resourceNodeCode = "GSB_Operation_Supplier";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/supplier/salesman/update";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("修改业务员");
		}

		createBean(beanPath, "验证", ActionUpdateSalesmanVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionUpdateSalesmanPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionUpdateSalesmanWriteBack.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
