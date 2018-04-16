package com.gongsibao.panda.supplier.order.workspace.audit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.AuditCarryoverListPart;
 
/*结转审核*/
public class AuditCarryoverWorkspaceTest extends WorkspaceCreationBase{
	
	@Override
	@Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/carryover/list";
        listPartName = formPartName = "结转审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Carryover";
        
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/audit/audit-carryover-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditCarryoverListPart.class.getName();
        listPartServiceController = AuditCarryoverListPart.class.getName();
        
        listFilter = "type_id=" + AuditLogType.Jzsh.getValue()+ " AND add_user_id='{userId}' ";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("结转订单");
            datagrid.setToolbar ("");
            datagrid.setAutoQuery (true);
        }
        PDatagridColumn column = null;
        column = addColumn(datagrid, "id", "操作", ControlTypes.TEXT_BOX, 100, true);{
        	column.setAlign(DatagridAlign.CENTER);
        	column.setFormatter("return controllerauditLogList.operateFormatter(value,row,index)");
        }
        column = addColumn(datagrid, "formId", "来源Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setVisible(false);
        }
        column = addColumn(datagrid, "carryover.formOrderId", "订单Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setSystem(true);
        	column.setVisible(false);
        }
        addColumn(datagrid, "carryover.formOrderNo", "结转来源订单号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "carryover.toOrderNo", "结转去向订单号", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "carryover.amount", "结转金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        column = addColumn(datagrid, "carryover.createTime", "创建结转时间", ControlTypes.DATETIME_BOX, 100);{
        	column.setOrderbyMode (OrderbyMode.DESC);
        }
        addColumn(datagrid, "carryover.creator", "结转创建人", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        PQueryItem item = null;
        queryProject.setColumnCount (3);

        item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("结转来源/去向订单号");
        }
        addQueryItem(queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "carryover.creator", "结转创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "carryover.createTime", "创建结转时间", ControlTypes.DATE_BOX);

        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
    }
}
