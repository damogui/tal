package com.gongsibao.panda.operation.workspace.order;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.panda.operation.workspace.crm.CustomerALLWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;
import com.gongsibao.tools.PToolbarHelper;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by zhangchao on 2018/3/9.
 */
public class OrderALLWorkspaceTest extends SalesmanOrderAllWorkspaceTest {


    @Before
    public void setup() {

        super.setup();

        //entity = SoOrder.class;// 实体
        urlList = "/operation/order/salesman/all/list";// 列表的url
        resourceNodeCode = "Operation_Order_Salesman_All";
        listToolbarPath = "operation/order/orderall/edit";
        //listPartName = formPartName = "全部订单";
        //meta = MtableManager.getMtable(entity);// 获取实体元数据
        //formPartName = listPartName = meta.getName();
        //listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/crm/platform/js/customer-all-list.part.js|/gsb/gsb.custom.query.controls.js";
        //listFilter = "id not in (SELECT customer_id from n_crm_customer_task GROUP BY customer_id)";
        //listPartJsController = NCustomerAllListPart.class.getName();
        //listPartServiceController = NCustomerAllListPart.class.getName();
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
            item.setName("批量订单转移");
            item.setSeq(9);
            item.setCommand("{controller}.batchOrderTran(1);");
            toolbar.getItems().add(item);
        }

        return toolbar;
    }


    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.view);//权限挂在哪一个下面
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setBasePath("panda/datagrid/row/edit");
            toolbar.setPath("/operation/roworderall/toolbar");
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
            item.setOperationType(ot1);
            item.setCommand("{controller}.orderTran();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }
}
