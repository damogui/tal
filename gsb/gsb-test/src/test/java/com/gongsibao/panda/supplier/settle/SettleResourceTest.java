package com.gongsibao.panda.supplier.settle;

import com.gongsibao.entity.igirl.OrderProdCase;
import com.gongsibao.igirl.base.IOrderProdCaseService;
import org.junit.Test;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class SettleResourceTest extends ResourceCreationBase {

    @Test
    public void run() {

        ResourceNode node = resourceNodeService.byCode("Gsb_Supplier_System");
        this.createResourceNodeVouchers(node);
    }

    @Override
    protected void createResourceNodeVouchers(ResourceNode node) {

        ResourceNode menuNode = this.createResourceNodeCategory("结算管理", "Gsb_Supplier_Settle", node.getId());
        {
            ResourceNode menuNode1 = this.createResourceNodeCategory("我的结算", "Gsb_Supplier_Settle_My", menuNode.getId());
            {
                this.createResourceNodeVoucher(OrderProdCase.class.getName(), "待结算列表", "Gsb_Supplier_My_Unsettle", IOrderProdCaseService.class.getName(), menuNode1.getId());
                this.createResourceNodeCategory("结算单列表", "Gsb_Supplier_MySettled", menuNode1.getId());
            }
        }
    }

}
