package com.gongsibao.panda.supplier.order.workspace.interactive;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.interactive.OperationPoolListPart;

public class InteractiveOperationPoolWorkspaceTest extends InteractiveMyInChargeWorkspaceTest {

    @Before
    public void setup() {
        super.setup();

        listPartName = formPartName = "操作订单池";
        urlList = "/crm/order/interactive/operation/pool/list";
        resourceNodeCode = "Gsb_Supplier_Order_Interactive_Operation_Pool";
        listToolbarPath = "/crm/order/operationpool/list";
        List<String> ss = new ArrayList<>();
        ss.add("/gsb/platform/trade/js/prod/interactive-myincharge-list.part.js");
        ss.add("/gsb/platform/trade/js/prod/select-salesman.ctrl.js");
        ss.add("/gsb/platform/trade/js/prod/interactive-operation-pool-list.part.js");
        ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
        listPartImportJs = StringManager.join("|", ss);
        listPartServiceController = OperationPoolListPart.class.getName();
        listPartJsController = OperationPoolListPart.class.getName();
        isSingleSelect = false;
        
    }

    @Test
    public void createListToolbar() {
        ResourceNode node = resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.view);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("操作订单池");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("addFollowUp");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("跟进");
            item.setSeq(3);
            item.setOperationType(ot1);
            item.setCommand("{controller}.addFollowUp();");
            toolbar.getItems().add(item);
        }

        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("addFollowUp");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("批量添加负责人");
            item.setSeq(3);
            item.setOperationType(ot1);
            item.setCommand("{controller}.addBatchPrincipal();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }

}