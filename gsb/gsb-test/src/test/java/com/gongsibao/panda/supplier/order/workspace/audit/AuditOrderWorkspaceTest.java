package com.gongsibao.panda.supplier.order.workspace.audit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
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
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.AuditOrderListPart;

/**
 * Created by win on 2018/3/20.
 */
/*订单审核(改价审核)*/
public class AuditOrderWorkspaceTest  extends WorkspaceCreationBase {
	private String listrowToolbarPath = "/audit/rowOrderAdd/toolbar";
	
	@Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/ortder/list";
        listPartName = formPartName = "订单审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Order";
        
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/audit/audit-order-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditOrderListPart.class.getName();
        listPartServiceController = AuditOrderListPart.class.getName();
        //过滤的就是订单改价审核
        listFilter = "type_id=" + AuditLogType.Ddgj.getValue()+ " AND add_user_id='{userId}' ";
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
			item.setCode("auditOrder");
			item.setName("审核");
			item.setSeq(2);
			item.setCommand("{controller}.auditOrder();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
	
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("订单审核");
            datagrid.setToolbar (listrowToolbarPath);
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        column = addColumn(datagrid, "formId", "来源Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setVisible(false);
        }
        column = addColumn(datagrid, "soOrder.id", "订单Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setSystem(true);
        	column.setVisible(false);
        }
        addColumn (datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 300);
        addColumn (datagrid, "soOrder.companyIntention.name", "关联企业", ControlTypes.TEXT_BOX, 200);
        
        addColumn (datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        
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
            item.setTooltip("订单编号、渠道订单编号、关联公司");
            item.setWidth(350);
        }

        addQueryItem (queryProject, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "soOrder.owner.name", "订单创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "soOrder.createTime", "创建日期", ControlTypes.DATE_BOX);

        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
    }
}



