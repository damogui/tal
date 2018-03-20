package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.AuditPayListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by win on 2018/3/20.
 */
/*回款业绩审核*/
public class AuditPayPerformanceWorkspaceTest  extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup ();
        entity = Pay.class;
        urlList = "/crm/order/audit/pay/list";
        listPartName = formPartName = "回款业绩审核";//回款业绩审核
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Pay_Audit_Performance";
        listToolbarPath = "crm/audit/pay/edit";
        listPartImportJs = "/gsb/platform/trade/js/audit-pay-list.js";
        listPartJsController = AuditPayListPart.class.getName();
    }

    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        // OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("回款业绩审核");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);
        }

        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconExtr,
                "查看审核记录", null, 1, "{controller}.add();");
        toolbar.getItems ().add (item);
        item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconCheck,
                "审核", null, 2, "{controller}.add();");
        toolbar.getItems ().add (item);
        return toolbar;
    }



    /*进行设置工具栏*/
    @Test
    public void saveListToolbar() {

        PToolbar toolbar = createListToolbar ();
        if (toolbar != null) {

            toolbarService.save (toolbar);
        }
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("回款业绩审核");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 60, true);
        column=addColumn (datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 120);//需要拼接
         {


             column.setFormatter("return controllerpayList.orderNameFormatter(value,row,index);");

        }
        addColumn (datagrid, "payForOrderCount", "是否一笔多单", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payWayType", "是否在线支付", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "amount", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "createTime", "回款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn (datagrid, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX, 100);


        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (3);

        PQueryItem item = null;
        item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("订单编号");
            item.setWidth(350);
        }


        addQueryItem (queryProject, "offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "payWayType", "是否在线支付", ControlTypes.ENUM_BOX);

        addQueryItem (queryProject, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "createTime", "回款业绩创建时间", ControlTypes.DATE_BOX);



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
