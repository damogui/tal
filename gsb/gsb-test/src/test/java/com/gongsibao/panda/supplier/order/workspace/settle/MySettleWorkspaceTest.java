package com.gongsibao.panda.supplier.order.workspace.settle;

import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.trade.web.settle.SettleListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class MySettleWorkspaceTest extends WorkspaceCreationBase {

    @Override
    @Before
    public void setup() {
        urlList = "/supplier/settle/settleOrder/myList";

        entity = Settle.class;
        meta = MtableManager.getMtable(entity);

        formPartName = listPartName = "我的结算单";
        resourceNodeCode = "Gsb_Supplier_MySettled";

        listPartServiceController = SettleListPart.class.getName();
        listPartJsController = SettleListPart.class.getName();

        listPartImportJs = "/gsb/supplier/settle/js/settle.list.part.js";
        listToolbarPath="/supplier/settle/list";
    }

    @Test
    public void fromToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("工具栏");
            toolbar.setResourceNode(node);
        }
        toolbarService.save(toolbar);
    }

        @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
//        datagrid.setToolbar("panda/datagrid/row/edit");
        datagrid.setFit(true);
        addColumn(datagrid, "id", "结算编号", ControlTypes.TEXT_BOX, 100).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "handleStatus", "结算状态", ControlTypes.ENUM_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "totalAmount", "总金额", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "totalCost", "成本", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "totalCharge", "服务费", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "commission", "佣金", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "tax", "税额", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "taxRate", "税率%", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "createTime", "创建时间", ControlTypes.NUMBER_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "memo", "备注", ControlTypes.NUMBER_BOX, 200).setAlign(DatagridAlign.CENTER);
        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "id", "结算编号", ControlTypes.NUMBER_BOX);
        addQueryItem(queryProject, "handleStatus", "结算状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "createTime", "时间", ControlTypes.DATE_BOX).setWidth(400);
        return queryProject;
    }

    public void doOperation() {
        ResourceNode node = resourceService.byCode(resourceNodeCode);
        operationService.addOperation(node, OperationTypes.view);
    }

}
