package com.gongsibao.panda.platform.trade.workspace.audit;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditCarryoverWorkspaceTest;

public class CarryoverAuditWorkspaceTest extends AuditCarryoverWorkspaceTest{
	@Before
	public void setup() {

		super.setup();
		urlList = "/trade/audit/carryover/list";
		resourceNodeCode = "GSB_Trade_Audit_Carryover";
		listFilter = "type_id=" + AuditLogType.Jzsh.getValue()+ " AND add_user_id='{userId}' ";
	}
	@Test
	public void createRowToolbar() {
    	
    }
}
