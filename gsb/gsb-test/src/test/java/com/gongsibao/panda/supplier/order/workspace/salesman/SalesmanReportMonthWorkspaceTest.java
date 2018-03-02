package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.SoOrder;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

/*月统计*/
public class SalesmanReportMonthWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlList ="/crm/order/salesman/month/report";
        listPartName = formPartName = "月统计";
        meta = MtableManager.getMtable (entity);
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Month_Report";

        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/gsb.custom.query.controls.js";///gsb/crm/sys/js/sys-salesman-list-part.js|
//        List<String> ss = new ArrayList<String> ();
//        ss.add("/gsb/trade/js/salesman-order-add-form.part.js");
//        ss.add("/gsb/gsb.customer.controls.js");
//        formJsImport = StringManager.join("|", ss);
//        formJsController = SalesmanAddOrderFormPart.class.getName();
//        formServiceController = SalesmanAddOrderFormPart.class.getName();
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("月统计");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "addTime", "回款日期", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "办理名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "关联企业", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "refundStatus.name", "退单状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "原价金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "应付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "paidPrice", "已付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "分期付款", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "开发票", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "操作员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "下单人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (6);

        addQueryItem (queryProject, "no", "编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "办理名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "客户创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "owner.name", "下单人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "下单人电话", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "关联企业", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "type", "订单类型", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "产品分类", ControlTypes.TEXT_BOX);

        addQueryItem (queryProject, "no", "下单方式", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "回款日期", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "分期付款", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "开发票", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "创建日期", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        //今天 昨天 本周 本月




        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
        operationService.addOperation (node, OperationTypes.update);
        operationService.addOperation (node, OperationTypes.delete);
    }

}
