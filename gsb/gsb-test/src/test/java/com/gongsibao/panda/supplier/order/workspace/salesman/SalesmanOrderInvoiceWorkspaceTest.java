package com.gongsibao.panda.supplier.order.workspace.salesman;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.web.SalesmanOrderInvoiceListPart;

/**
 * Created by zhangchao on 2018/3/16.
 */
public class SalesmanOrderInvoiceWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = OrderInvoiceMap.class;
        urlList = "/crm/order/salesman/invoice/list";
        listPartName = formPartName = "发票管理";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Invoice";
        listPartImportJs = "/gsb/platform/trade/js/salesman-order-invoice-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartServiceController = SalesmanOrderInvoiceListPart.class.getName();
        listPartJsController = SalesmanOrderInvoiceListPart.class.getName();
        listFilter = "soOrder.owner_id = '{userId}'";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("发票管理");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.accountType", "新老客户签单", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 200);
        column = addColumn(datagrid, "soOrder.totalPrice", "订单金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "soOrder.payablePrice", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "invoice.amount", "发票金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "invoice.typeId", "发票类型", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "invoice.title", "发票抬头", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.companyId", "开票公司", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "invoice.createTime", "发票申请时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "invoice.creator", "发票申请人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.auditStatusId", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

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
            item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、签单企业");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "invoice.auditStatusId", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "invoice.typeId", "发票类型", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "发票申请人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "createTime", "发票申请时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}
