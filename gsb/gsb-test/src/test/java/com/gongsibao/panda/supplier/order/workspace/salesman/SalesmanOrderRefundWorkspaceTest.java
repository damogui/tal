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

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.trade.web.OrderSalesmanRefundListPart;

/*退款订单*/
public class SalesmanOrderRefundWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = NDepRefund.class;
        urlList = "/crm/order/salesman/refund/list";
        listPartName = formPartName = "我的退款";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Refund";
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartServiceController = OrderSalesmanRefundListPart.class.getName();
        listFilter = "employee_id = '{userId}'";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("退款订单");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "order.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "order.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "order.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "order.companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "order.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "order.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "order.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "refund.amount", "退款金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "refund.setOfBooks.name", "退款账套", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "refund.u8Bank.name", "退款方式", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "refund.refundType", "退款类别", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "amount", "我的退款业绩额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "refund.auditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "refund.createTime", "退款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "refund.depRefundAuditPassTime", "退款审核通过时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "order.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "退款创建人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "order.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

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
        addQueryItem(queryProject, "order.prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "refund.auditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "order.payStatus", "订单付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "refund.refundType", "退款类别", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "order.owner.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "refund.creator", "退款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "refund.createTime", "退款业绩创建时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "order.createTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
