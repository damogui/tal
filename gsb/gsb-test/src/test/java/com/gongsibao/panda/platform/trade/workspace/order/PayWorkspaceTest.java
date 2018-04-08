package com.gongsibao.panda.platform.trade.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPayWorkspaceTest;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by guojia on 2018/4/8.
 * 交易中心回款
 */
public class PayWorkspaceTest  extends SalesmanOrderPayWorkspaceTest {
    private String listrowToolbarPath = "/crm/row/pay/toolbar";
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/pay/list";
        resourceNodeCode = "Operation_Order_Pay";
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
            toolbar.setName ("回款查看");
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
