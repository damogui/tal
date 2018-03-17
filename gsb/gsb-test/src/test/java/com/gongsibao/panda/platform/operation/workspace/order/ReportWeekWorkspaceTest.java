package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class ReportWeekWorkspaceTest extends SalesmanOrderAllWorkspaceTest{
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/report/week";
		resourceNodeCode = "Operation_Order_Week_Report";
		listToolbarPath = "panda/datagrid/edit";
	}	
    @Test
    public void createListToolbar() {
    	
    }
    

    @Test
    public void createRowToolbar() {
    	
    }
}
