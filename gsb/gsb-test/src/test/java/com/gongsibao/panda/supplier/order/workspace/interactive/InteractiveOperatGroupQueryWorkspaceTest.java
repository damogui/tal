package com.gongsibao.panda.supplier.order.workspace.interactive;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.web.interactive.OperatGroupQueryList;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

public class InteractiveOperatGroupQueryWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {
        super.setup();
        entity = OrderProd.class;
        urlList = "/crm/order/interactive/operationgroup/query/list";
        listPartName = formPartName = "操作组查询";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Operating_Group_Query";
        listPartImportJs = "/gsb/platform/trade/js/prod/interactive-opera-group-query-list.part.js";
        listPartServiceController = OperatGroupQueryList.class.getName();
        listPartJsController = OperatGroupQueryList.class.getName();
        listFilter = "";
    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("操作组查询");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(false);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "订单明细号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "operationsGroup", "操作组名称", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "operator", "操作员", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "cityName", "地区", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "companyIntention.companyName", "明细订单公司", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.paidPrice", "订单应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.balance", "订单余额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "department.name", "业务员归属部门", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.fistPayTime", "付首款时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "soOrder.createTime", "下单时间", ControlTypes.DATETIME_BOX, 100);
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
            item.setTooltip("订单编号、公司名称、客户手机号");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "productName", "产品名称", ControlTypes.TEXT_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}
