package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanOrderCarryoverListPart;
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


/*结转订单*/
public class SalesmanOrderCarryoverWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = NOrderCarryover.class;
        urlList = "/crm/order/salesman/carryover/list";
        listPartName = formPartName = "结转订单";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Carryover";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";///gsb/crm/sys/js/sys-salesman-list-part.js|
        listPartServiceController = SalesmanOrderCarryoverListPart.class.getName();
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
            datagrid.setName("结转订单");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "formOrderNo", "结转来源订单号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "toOrderNo", "结转去向订单号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "amount", "结转金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "auditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "createTime", "创建结转时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "结转创建人", ControlTypes.TEXT_BOX, 100);
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
            item.setTooltip("结转来源/去向订单号");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "auditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "creator", "结转创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "createTime", "创建结转时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }


}
