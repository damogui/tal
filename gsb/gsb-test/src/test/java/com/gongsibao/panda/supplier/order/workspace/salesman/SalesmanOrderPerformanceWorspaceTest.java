package com.gongsibao.panda.supplier.order.workspace.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.web.SalesmanOrderPerformanceListPart;

/*订单业绩列表*/
public class SalesmanOrderPerformanceWorspaceTest extends WorkspaceCreationBase {
    private String listrowToolbarPath = "/crm/row/order/per/toolbar";
    @Before
    public void setup() {

        super.setup();
        entity = NDepReceivable.class;
        urlList = "/crm/order/salesman/performance/list";
        //urlForm = "/crm/order/salesman/coperformance";
        listPartName = formPartName = "订单业绩";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Performance";
        listPartImportJs = "/gsb/platform/trade/js/salesman-order-performance-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartServiceController = SalesmanOrderPerformanceListPart.class.getName();
        listPartJsController = SalesmanOrderPerformanceListPart.class.getName();
        listFilter = "salesman_id = '{userId}'";
        listToolbarPath="";
    }



    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("查看");
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

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("订单业绩");
            datagrid.setToolbar(listrowToolbarPath);
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "soOrder.id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 250);
        addColumn(datagrid, "soOrder.companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "soOrder.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);

        addColumn(datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        column = addColumn(datagrid, "amount", "我的订单业绩额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "statusType", "审核状态", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "createTime", "订单业绩创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 60);{

            column.setSystem (true);
            column.setVisible (false);
            column.setOrderbyMode (OrderbyMode.DESC);


        }
        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        PQueryItem item = null;
        queryProject.setColumnCount(3);
        item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("订单编号、渠道订单编号、签单公司");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "keyword", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "statusType", "审核状态", ControlTypes.ENUM_BOX);
       addQueryItem(queryProject, "soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "createTime", "业绩创建时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "orderCreateTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
