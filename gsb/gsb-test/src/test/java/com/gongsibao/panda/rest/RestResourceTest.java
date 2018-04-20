package com.gongsibao.panda.rest;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.panda.platform.bd.ResourceTest;
import com.gongsibao.trade.base.IOrderService;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RestResourceTest extends ResourceCreationBase {

    @Before
    public void setup() {
        parentNodeName = "Rest接口";
        parentNodeCode = "Gsb_Rest";
        pluginName = "Rest接口";
        seq = 100;
        entityClass = ResourceNode.class;
    }

    @Override
    protected void createResourceNodeVouchers(ResourceNode node) {

        ResourceNode node1 = this.createResourceNodeCategory("Rest接口", "Gsb_Rest_Interface", node.getId());
        {
            this.createResourceNodeVoucher(SoOrder.class.getName(), "Rest订单支付", "Gsb_Rest_Interface_Order_Pay", IOrderService.class.getName(), node1.getId());
           /* ResourceNode node2 = this.createResourceNodeCategory("Rest订单", "Gsb_Rest_Interface_Order", node1.getId());
            {
                this.createResourceNodeVoucher(SoOrder.class.getName(), "Rest订单支付", "Gsb_Rest_Interface_Order_Pay", IOrderService.class.getName(), node2.getId());
            }*/
        }
    }
}