package com.gongsibao.panda.platform.trade.workspace.audit;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.audit.AuditCarryoverWorkspaceTest;

public class CarryoverAuditWorkspaceTest extends AuditCarryoverWorkspaceTest{
	@Before
	public void setup() {

		super.setup();
		urlList = "/trade/audit/carryover/list";
		resourceNodeCode = "GSB_Trade_Audit_Carryover";
		listFilter = "";
	}
	@Test
	public void createRowToolbar() {
    	
    }
}
