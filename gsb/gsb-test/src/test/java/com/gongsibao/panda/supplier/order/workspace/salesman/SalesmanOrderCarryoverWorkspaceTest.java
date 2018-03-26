package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.trade.web.SalesmanOrderCarryoverListPart;


/*结转订单*/
public class SalesmanOrderCarryoverWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = NOrderCarryover.class;
        urlList = "/crm/order/salesman/carryover/list";
        listPartName = formPartName = "结转订单";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Carryover";
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/salesman-order-carryover-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = SalesmanOrderCarryoverListPart.class.getName();
        listPartServiceController = SalesmanOrderCarryoverListPart.class.getName();
        listFilter = "creator_id='{userId}'";
        
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
        column = addColumn(datagrid, "formOrderId", "订单Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setSystem(true);
        	column.setVisible(false);
        }
        addColumn(datagrid, "toOrderNo", "结转去向订单号", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "amount", "结转金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
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
