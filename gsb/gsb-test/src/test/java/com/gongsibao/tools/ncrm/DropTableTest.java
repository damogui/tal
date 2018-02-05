package com.gongsibao.tools.ncrm;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INServiceConfigService;
import com.gongsibao.entity.crm.NServiceConfig;

/**
 * @ClassName: DropTableTest
 * @Description:TODO 删除CRM相关表结构
 * @author: 韩伟
 * @date: 2018年2月1日 下午3:52:53
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class DropTableTest {

	@Test
	public void run() {

		NServiceConfig entity = null;
		INServiceConfigService service = ServiceFactory.create(INServiceConfigService.class);

		for (int i = 0; i < 200000; i++) {

			entity = new NServiceConfig();
			entity.toNew();
			entity.setCreator("Test" + 1);
			service.save(entity);
		}
	}
}
