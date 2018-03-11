package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class ContractWorkspaceTest extends SalesmanOrderAllWorkspaceTest{
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/contract/list";
		resourceNodeCode = "Operation_Order_Contract";
		listToolbarPath = "panda/datagrid/edit";
	}	
    @Test
    public void saveListToolbar() {
    	
    }
    

    @Test
    public void saveRowToolbar() {
    	
    }
}
