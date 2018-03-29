package com.gongsibao.panda.platform.trade.workspace.audit;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditStagingWorkspaceTest;

/**   
 * @ClassName:  InstallmentWorkspaceTest   
 * @Description:TODO 分期审核
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:08:02   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class StagingAuditWorkspaceTest extends AuditStagingWorkspaceTest {
	
	@Before
	public void setup() {
		super.setup();
		urlList = "/trade/audit/staging/list";
		resourceNodeCode = "GSB_Trade_Audit_Staging";
		listFilter = "type_id=" + AuditLogType.Fqsq.getValue()+ " AND add_user_id='{userId}' ";
	}
	
    @Test
	public void createRowToolbar() {
    	
    }
}
