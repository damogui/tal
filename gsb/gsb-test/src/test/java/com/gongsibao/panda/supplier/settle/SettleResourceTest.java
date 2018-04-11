package com.gongsibao.panda.supplier.settle;

import com.gongsibao.entity.igirl.settle.OrderProdCase;
import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.igirl.settle.base.IOrderProdCaseService;
import com.gongsibao.igirl.settle.base.ISettleService;
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
                this.createResourceNodeVoucher(Settle.class.getName(), "我的结算单", "Gsb_Supplier_MySettled", ISettleService.class.getName(), menuNode1.getId());
                this.createResourceNodeVoucher(Settle.class.getName(), "结算单信息", "Gsb_Supplier_Settle_form", ISettleService.class.getName(), menuNode1.getId());
            }
        }
    }

}
