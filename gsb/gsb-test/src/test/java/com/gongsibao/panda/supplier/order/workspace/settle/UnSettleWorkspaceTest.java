package com.gongsibao.panda.supplier.order.workspace.settle;

import com.gongsibao.entity.trade.settle.OrderProdSettle;
import com.gongsibao.trade.web.settle.UnSettleListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class UnSettleWorkspaceTest extends WorkspaceCreationBase {

    @Override
    @Before
    public void setup() {
        urlList = "/supplier/settle/unSettle/list";
//        urlForm = "/igirl/settle/unSettle/form";
        entity = OrderProdSettle.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = "待结算列表";
        resourceNodeCode = "Gsb_Supplier_My_Unsettle";
        listPartServiceController = UnSettleListPart.class.getName();
        listPartJsController = UnSettleListPart.class.getName();
        listPartImportJs = "/gsb/supplier/settle/js/unsettle.list.part.js";
//        formServiceController = TradeMarkCasePart.class.getName();
//        formJsController = TradeMarkCasePart.class.getName();
//        formJsImport = "/gsb/igirl/js/markcase.form.part.js";
        //formJsImport = "/gsb/supplier/settle/js/unsettle.list.part.js";
        listToolbarPath = "/supplier/settle/unSettle/toolbar";
    }

    @Test
    public void fromToolbar() {
        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.operation);

        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            //toolbar.setBasePath("panda/datagrid/edit");
            toolbar.setPath(listToolbarPath);
            toolbar.setName("结算工具栏");
            toolbar.setResourceNode(node);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("");
            item.setIcon("fa fa-link");
            item.setName("结算");
            item.setCommand(null);
            item.setOperationType(ot1);
            item.setSeq(3000);
            item.setCommand("{controller}.doSettle();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
//        datagrid.setToolbar("panda/datagrid/row/edit");
        datagrid.setShowCheckbox(true);
        datagrid.setSingleSelect(false);
        datagrid.setFit(true);
        addColumn(datagrid, "orderProdId", "订单明细编号", ControlTypes.TEXT_BOX, 200).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "soOrder.no", "订单号", ControlTypes.TEXT_BOX, 100).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "caseId", "案件编号", ControlTypes.TEXT_BOX, 100).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "caseType", "方案类型", ControlTypes.ENUM_BOX, 100).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "orderProd.productName", "产品名称", ControlTypes.TEXT_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "account.mobilePhone", "客户手机", ControlTypes.TEXT_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "account.realName", "客户名称", ControlTypes.TEXT_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "cost", "成本", ControlTypes.TEXT_BOX, 100).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "charge", "服务费", ControlTypes.TEXT_BOX, 100).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "memo", "说明", ControlTypes.TEXT_BOX, 200).setAlign(DatagridAlign.CENTER);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "soOrder.no", "订单号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "orderProdId", "订单明细ID", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "orderProd.productName", "产品名称", ControlTypes.TEXT_BOX);

        return queryProject;
    }
    public void doOperation() {
        ResourceNode node = resourceService.byCode(resourceNodeCode);
        operationService.addOperation(node, OperationTypes.operation);
    }
}
