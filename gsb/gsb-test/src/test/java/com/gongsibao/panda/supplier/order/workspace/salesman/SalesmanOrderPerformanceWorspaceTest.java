package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanOrderPerformanceListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/*订单业绩*/
public class SalesmanOrderPerformanceWorspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = SoOrder.class;
        urlList = "/crm/order/salesman/performance/list";
        listPartName = formPartName = "订单业绩";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Performance";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/platform/trade/js/salesman-order-performance-list.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartServiceController = SalesmanOrderPerformanceListPart.class.getName();
        listPartJsController = SalesmanOrderPerformanceListPart.class.getName();
        listFilter = " pkid in (select order_id from n_dep_receivable where employee_id = {userId} OR creator_id = {userId})";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("订单业绩");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 250);
        addColumn(datagrid, "companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        column = addColumn(datagrid, "amount", "订单业绩分配金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "depReceivableAuditStatusId", "审核状态", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
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
        addQueryItem(queryProject, "depReceivableAuditStatusId", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "ywyName", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
