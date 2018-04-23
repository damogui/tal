package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import com.gongsibao.trade.web.SalesmanOrderPayListPart;
import com.gongsibao.trade.web.platform.PayPerformanceListPart;

import org.junit.Test;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/*运营平台回款业绩*/
public class ReceivedWorkspaceTest extends SalesmanOrderReceivedWorkspaceTest {

    private String listrowToolbarPath = "/crm/department/roworderreceive/toolbar";
    @Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/received/list";
		resourceNodeCode = "Operation_Order_Received";
		listFilter = "";
		listPartServiceController = PayPerformanceListPart.class.getName ();
	}

    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("运营平台回款业绩");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("view");
            item.setName("查看");
            item.setSeq(1);
            item.setCommand("{controller}.view();");
            toolbar.getItems().add(item);
        }


        toolbarService.save(toolbar);
    }

}
