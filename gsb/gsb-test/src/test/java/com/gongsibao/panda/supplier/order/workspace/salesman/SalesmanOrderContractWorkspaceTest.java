package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.Contract;
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

/*合同管理*/
public class SalesmanOrderContractWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {
        super.setup ();
        entity = Contract.class;
        urlList = "/crm/order/salesman/contract/list";
        listPartName = formPartName = "合同管理";
        meta = MtableManager.getMtable (entity);
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Contract";

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
            datagrid.setName ("合同管理");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "contractPrice", "业绩总额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "realAmount", "合同总额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.prodName", "材料撰写费", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "liquidatedDamages", "违约金", ControlTypes.TEXT_BOX, 100);

        addColumn (datagrid, "soOrder.no", "分期付款", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "updatorId", "申请人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "updateTime", "申请时间", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.no", "下单人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.totalPrice", "下单人电话", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.totalPrice", "业务员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.totalPrice", "关联企业", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "createTime", "下单时间", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "auditStatusId", "审核状态", ControlTypes.TEXT_BOX, 100);


        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (6);

        addQueryItem (queryProject, "soOrderno.no", "订单编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "soOrderno.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);

        addQueryItem (queryProject, "soOrder.owner.name", "下单人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "soOrderno.no", "下单人电话", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "soOrderno.no", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "soOrderno.no", "关联企业", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "createTime", "日期", ControlTypes.TEXT_BOX);





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
