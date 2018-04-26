package com.gongsibao.panda.action.supplier.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.supplier.action.salesman.delete.ActionDeleteSalesmanPersist;
import com.gongsibao.supplier.action.salesman.delete.ActionDeleteSalesmanVerify;
import com.gongsibao.supplier.action.salesman.delete.ActionDeleteSalesmanWriteBack;

public class SalesmanActionDeleteTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "GSB_Operation_Supplier";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/supplier/salesman/delete";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("删除业务员");
		}

		createBean(beanPath, "验证", ActionDeleteSalesmanVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionDeleteSalesmanPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionDeleteSalesmanWriteBack.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
