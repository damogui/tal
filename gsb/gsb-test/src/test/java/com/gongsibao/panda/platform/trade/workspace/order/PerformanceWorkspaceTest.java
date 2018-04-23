package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPerformanceWorspaceTest;
import com.gongsibao.trade.web.platform.OrderPerformanceListPart;

import org.junit.Test;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;


/*运营平台订单业绩*/
public class PerformanceWorkspaceTest extends SalesmanOrderPerformanceWorspaceTest {
    private String listrowToolbarPath = "/crm/row/per/toolbar";
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/performance/list";
        resourceNodeCode = "Operation_Order_Performance";
        listPartServiceController = OrderPerformanceListPart.class.getName();
        listFilter = "";
    }
    @Override
    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listrowToolbarPath);
            toolbar.setName ("订单业绩查看");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("view");
            item.setName ("查看");
            item.setSeq (1);
            item.setCommand ("{controller}.detail();");
            toolbar.getItems ().add (item);
        }


        toolbarService.save (toolbar);
    }



}
