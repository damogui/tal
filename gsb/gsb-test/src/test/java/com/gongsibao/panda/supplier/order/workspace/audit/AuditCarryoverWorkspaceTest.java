package com.gongsibao.panda.supplier.order.workspace.audit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.AuditCarryoverListPart;
 
/*结转审核*/
public class AuditCarryoverWorkspaceTest extends WorkspaceCreationBase{
	private String listrowToolbarPath = "/audit/rowCarryOver/toolbar";
    
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
		ss.add("/gsb/platform/trade/js/audit-carryover-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditCarryoverListPart.class.getName();
        listPartServiceController = AuditCarryoverListPart.class.getName();
        
        listFilter = "type_id=" + AuditLogType.Jzsh.getValue()+ " AND add_user_id='{userId}' ";
    }

    @Test
	public void createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listrowToolbarPath);
			toolbar.setName("审核");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("auditCarryOver");
			item.setName("审核");
			item.setSeq(2);
			item.setCommand("{controller}.auditCarryOver();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("结转订单");
            datagrid.setToolbar (listrowToolbarPath);
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);

        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "carryover.formOrderNo", "结转来源订单号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "carryover.toOrderNo", "结转去向订单号", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "carryover.amount", "结转金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "carryover.createTime", "创建结转时间", ControlTypes.DATETIME_BOX, 100);
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
