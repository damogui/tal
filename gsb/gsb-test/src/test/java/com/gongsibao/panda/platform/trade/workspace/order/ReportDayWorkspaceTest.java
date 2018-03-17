package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class ReportDayWorkspaceTest extends SalesmanOrderAllWorkspaceTest{
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/report/day";
		resourceNodeCode = "Operation_Order_Day_Report";
		listToolbarPath = "panda/datagrid/edit";
	}	
    @Test
    public void createListToolbar() {
    	
    }
    

    @Test
    public void createRowToolbar() {
    	
    }
}
