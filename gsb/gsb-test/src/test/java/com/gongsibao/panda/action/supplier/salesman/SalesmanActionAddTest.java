package com.gongsibao.panda.action.supplier.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.supplier.action.salesman.add.ActionAddSalesmanPersist;
import com.gongsibao.supplier.action.salesman.add.ActionAddSalesmanVerify;
import com.gongsibao.supplier.action.salesman.add.ActionAddSalesmanWriteBack;

public class SalesmanActionAddTest extends BaseActionTest{
	
	@Before
	public void setup() {

		resourceNodeCode = "GSB_Operation_Supplier";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/supplier/salesman/add";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("创建业务员");
		}

		createBean(beanPath, "验证", ActionAddSalesmanVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionAddSalesmanPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionAddSalesmanWriteBack.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
