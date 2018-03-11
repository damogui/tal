package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.tools.PToolbarHelper;

/**
 * Created by zhangchao on 2018/3/9.
 */
public class OrderPoolWorkspaceTest extends OrderALLWorkspaceTest {


    private String listrowToolbarPath = "/operation/roworderpool/toolbar";

    @Before
    public void setup() {

        super.setup();

        urlList = "/operation/order/pool/list";// 列表的url
        resourceNodeCode = "Operation_Order_Pool";
        listPartName = formPartName = "订单池";
        listToolbarPath = "operation/order/orderpool/edit";
        listFilter = "(owner_id is null or owner_id=0)";
    }

    protected PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid grid = super.createDatagrid(node);
        grid.setToolbar(listrowToolbarPath);
        return grid;
    }

    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("批量分配");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }


        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("detail");
            item.setIcon(PToolbarHelper.iconExtr);
            item.setName("订单详情");
            item.setSeq(1);
            item.setCommand("{controller}.detail();");
            toolbar.getItems().add(item);
        }

        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("batchOrderTran");
            item.setIcon(PToolbarHelper.iconTran);
            item.setName("批量订单分配");
            item.setSeq(9);
            item.setCommand("{controller}.batchOrderTran(1);");
            toolbar.getItems().add(item);
        }

        return toolbar;
    }

    public PToolbar createRowToolbar() {
        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setBasePath("panda/datagrid/row/edit");
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("分配");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("orderTran");
            item.setName("分配");
            item.setSeq(2);
            item.setCommand("{controller}.orderTran();");
            toolbar.getItems().add(item);
        }

        return toolbar;
    }
}
