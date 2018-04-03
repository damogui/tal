package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.web.AuditPayListPart;
import com.gongsibao.trade.web.SalesmanOrderPayController;
import com.gongsibao.trade.web.SalesmanOrderReceivedListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * 我的回款
 */
public class SalesmanOrderPayWorkspaceTest  extends WorkspaceCreationBase {

    private String listrowToolbarPath = "/crm/roworderpay/toolbar";
    @Before
    public void setup() {
        super.setup ();
        entity = Pay.class;
        urlList = "/crm/order/salesman/pay/list";
        listPartName = formPartName = "我的回款";//我的回款
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Pay";
        listToolbarPath = "crm/salesman/pay/edit";
        listPartImportJs = "/gsb/platform/trade/js/salesman-order-pay-list.part.js";
        listPartJsController = SalesmanOrderPayController.class.getName ();
        listPartServiceController = SalesmanOrderPayController.class.getName ();
        //listFilter = "salesman_id = '{userId}'";
        listToolbarPath="";
        listFilter = " (pkid IN (SELECT pay_id FROM so_order_pay_map WHERE order_id IN (SELECT pkid FROM so_order WHERE owner_id = '{userId}' ORDER BY pkid DESC)) OR add_user_id = '{userId}' )";
    }


    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("回款查看");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("detail");
            item.setName("查看");
            item.setSeq(1);
            item.setCommand("{controller}.detail();");
            toolbar.getItems().add(item);
        }


        toolbarService.save(toolbar);
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("我的回款");
            datagrid.setToolbar (listrowToolbarPath);
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 60, true);
        // addColumn (datagrid, "u8Bank.name", "姓名", ControlTypes.TEXT_BOX, 100);
        column = addColumn (datagrid, "orderIds", "订单编号", ControlTypes.TEXT_BOX, 120);//需要拼接
        {
            // column.setFormatter("return controllerpayList.orderNameFormatter(value,row,index);");

        }
        addColumn (datagrid, "payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "payWayType", "是否在线支付", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "amount", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "confirmTime", "回款日期", ControlTypes.DATE_BOX, 100);
        addColumn (datagrid, "createTime", "回款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn (datagrid, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX, 100);


        return datagrid;
    }




    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (3);

        PQueryItem item = null;
        item = addQueryItem (queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip ("订单编号");
            item.setWidth (350);
        }


        addQueryItem (queryProject, "offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "payWayType", "是否在线支付", ControlTypes.ENUM_BOX);

        addQueryItem (queryProject, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "createTime", "回款业绩创建时间", ControlTypes.DATE_BOX);


        return queryProject;
    }
    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}
