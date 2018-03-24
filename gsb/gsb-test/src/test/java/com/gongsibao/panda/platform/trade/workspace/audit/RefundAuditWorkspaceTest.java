package com.gongsibao.panda.platform.trade.workspace.audit;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.audit.AuditRefundWorkspaceTest;

/**   
 * @ClassName:  RefundAuditWorkspaceTest   
 * @Description:TODO 退单审核
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:08:12   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class RefundAuditWorkspaceTest extends AuditRefundWorkspaceTest {

	@Before
	public void setup() {
		super.setup();
		urlList = "/trade/audit/refund/list";
		resourceNodeCode = "GSB_Trade_Audit_Refund";
		listFilter = "";
	}
	@Test
	public void createRowToolbar() {
    	
    }
}
