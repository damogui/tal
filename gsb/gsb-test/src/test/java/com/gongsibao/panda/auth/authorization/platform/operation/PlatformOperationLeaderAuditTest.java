package com.gongsibao.panda.auth.authorization.platform.operation;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

/**   
 * @ClassName:  PlatformOperationLeaderAuditTest   
 * @Description:TODO 运营经理拥有所有审核中心的权限
 * @author: 韩伟
 * @date:   2018年4月13日 下午5:57:40   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class PlatformOperationLeaderAuditTest extends AuthBaseTest{
	
	@Before
	public void setup() {
        roleCode = "Platform_Operation_Leader";
		super.setup();

	}
	
    protected void getResourceCodeList() {

      this.resourceNodeCodeList.add("GSB_Trade_Audit");
  }
}
