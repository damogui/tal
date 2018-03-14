package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanOrderReceivedListPart;
import org.apache.commons.collections.OrderedMap;
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

/*回款业绩*/
public class SalesmanOrderReceivedWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = OrderPayMap.class;
        urlList = "/crm/order/salesman/received/list";
        listPartName = formPartName = "回款业绩";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Received";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";///gsb/crm/sys/js/sys-salesman-list-part.js|
        listPartServiceController = SalesmanOrderReceivedListPart.class.getName();
        //listPartJsController = SalesmanOrderReceivedListPart.class.getName();
        //listFilter = "pkid in(select order_pay_map_id from n_dep_pay where employee_id = '{userId}' or creator_id = '{userId}')";
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
            datagrid.setName("回款业绩");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "pay.payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.payStatus", "订单付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "pay.successStatus", "支付状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "pay.payWayType", "支付类别", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "pay.onlineBankCodeId", "支付银行", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "pay.setOfBooks.name", "线下支付账套", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "pay.u8Bank.name", "线下支付方式", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "pay.payWayType", "付款类别", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "myOrderCutAmount", "我的回款业绩额", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "pay.offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "pay.payTime", "回款日期", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "pay.depPayCreateTime", "回款业绩创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "pay.depPayAuditPassTime", "回款业绩审核通过时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "pay.depPayCreateEmployeeName", "回款业绩创建人", ControlTypes.TEXT_BOX, 100);
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
        addQueryItem(queryProject, "pay.offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.payStatus", "订单付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "pay.successStatus", "支付状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "pay.payWayType", "支付类别", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "ywyName", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "depPayCreateEmployeeName", "回款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "pay.payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "depPayCreateTime", "回款业绩创建时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "orderCreateTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
