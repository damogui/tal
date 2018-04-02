package com.gongsibao.panda.supplier.settle;

import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;

public class SettleNavigationTest extends NavigationBase {

    protected void createPTree() {
        PNavigation tree = treeService.byPath("panda/gsb/supplier");
        this.doCreateTree(tree);
        grantPermission(tree);
        treeService.save(tree);
    }

    @Override
    protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode, String icon, String code, String name, String url, int seq) {

        PNavigationItem node = new PNavigationItem();
        {
            node.toNew();
            node.setCode(code);
            node.setName(name);
            node.setUrl(url);
            node.setParent(parentCode);
            node.setSeq(seq);
            node.setIcon(icon);
            node.setPathId(tree.getId());
            tree.getTreeNodes().add(node);
        }
        return node;
    }

    @Override
    protected void doCreateTree(PNavigation tree) {

        createPTreeNode(tree, null, null, "Gsb_Supplier_Settle", "结算管理", "", 2);
        {
            createPTreeNode(tree, "Gsb_Supplier_Settle", null, "Gsb_Supplier_Settle_My", "我的结算", "", 1);
            {
                createPTreeNode(tree, "Gsb_Supplier_Settle_My", null, "Gsb_Supplier_My_Unsettle", "未结算订单", "/igirl/settle/unSettle/list", 1);
                createPTreeNode(tree, "Gsb_Supplier_Settle_My", null, "Gsb_Supplier_MySettled", "结算单列表", "/igirl/settle/settleOrder/list", 2);
            }
        }
    }

}
