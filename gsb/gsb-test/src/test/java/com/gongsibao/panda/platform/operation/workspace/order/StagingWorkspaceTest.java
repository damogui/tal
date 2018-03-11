package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class StagingWorkspaceTest extends SalesmanOrderAllWorkspaceTest{
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/staging/list";
		resourceNodeCode = "Operation_Order_Staging";
		listToolbarPath = "panda/datagrid/edit";
	}	
    @Test
    public void saveListToolbar() {
    	
    }
    

    @Test
    public void saveRowToolbar() {
    	
    }
}
