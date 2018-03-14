package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.OrderSalesmanRefundListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/*退款订单*/
public class SalesmanOrderRefundWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = Refund.class;
        urlList = "/crm/order/salesman/refund/list";
        listPartName = formPartName = "退款订单";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Refund";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";///gsb/crm/sys/js/sys-salesman-list-part.js|
        listPartServiceController = OrderSalesmanRefundListPart.class.getName();
//        List<String> ss = new ArrayList<String> ();
//        ss.add("/gsb/platform/trade/js/salesman-order-add-form.part.js");
//        ss.add("/gsb/panda-extend/gsb.customer.controls.js");
//        formJsImport = StringManager.join("|", ss);
//        formJsController = SalesmanAddOrderFormPart.class.getName();
//        formServiceController = SalesmanAddOrderFormPart.class.getName();
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
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "soOrder.companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 250);
        addColumn(datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "amount", "退款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "setOfBooks.name", "退款账套", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "u8Bank.name", "退款方式", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "refundType", "退款类别", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "myDepRefundAmount", "我的退款业绩额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "auditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "createTime", "退款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "depRefundAuditPassTime", "退款审核通过时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "退款创建人", ControlTypes.TEXT_BOX, 100);
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
        addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "auditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.payStatus", "订单付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "refundType", "退款类别", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "ywyName", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "退款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "createTime", "退款业绩创建时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "orderCreateTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
