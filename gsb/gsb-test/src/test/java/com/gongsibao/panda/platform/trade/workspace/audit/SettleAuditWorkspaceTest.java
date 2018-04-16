package com.gongsibao.panda.platform.trade.workspace.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.AuditSettleListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class SettleAuditWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/trade/audit/settle/list";
        listPartName = formPartName = "结算审核";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "GSB_Trade_Audit_Settle";
        listToolbarPath = "";
        listPartImportJs = "/gsb/platform/trade/js/audit/audit-settle-list.part.js";
        listPartJsController = AuditSettleListPart.class.getName ();
        listPartServiceController = AuditSettleListPart.class.getName ();
        listFilter = "type_id=" + AuditLogType.Jssh.getValue() + " and add_user_id = '{userId}' ";
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
        PDatagridColumn column = addColumn(datagrid, "id", "操作", ControlTypes.TEXT_BOX, 120, true);
        {
            column.setFormatter("return controllerauditLogList.operateFormatter(value,row,index)");
            column.setAlign(DatagridAlign.CENTER);
        }
        addColumn(datagrid, "formId", "结算编号", ControlTypes.TEXT_BOX, 200).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.handleStatus", "结算状态", ControlTypes.ENUM_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.totalAmount", "总金额", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.totalCost", "成本", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.totalCharge", "服务费", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.commission", "佣金", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.tax", "税额", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.taxRate", "税率%", ControlTypes.NUMBER_BOX, 120).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.createTime", "创建时间", ControlTypes.NUMBER_BOX, 150).setAlign(DatagridAlign.CENTER);
        addColumn(datagrid, "settle.memo", "备注", ControlTypes.NUMBER_BOX, 200).setAlign(DatagridAlign.CENTER);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {
        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "formId", "结算编号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
//        addQueryItem(queryProject, "orderProdSettle.handleStatus", "结算状态", ControlTypes.ENUM_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.update);
    }
}
