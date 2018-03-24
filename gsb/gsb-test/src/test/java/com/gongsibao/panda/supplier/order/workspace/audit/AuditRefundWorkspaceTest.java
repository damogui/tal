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
import com.gongsibao.trade.web.AuditRefundListPart; 

/*退款审核*/
public class AuditRefundWorkspaceTest extends WorkspaceCreationBase{
	private String listrowToolbarPath = "/audit/rowRefund/toolbar";
	
	@Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/refund/list";
        listPartName = formPartName = "退款审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Refund";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/audit-refund-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditRefundListPart.class.getName();
        listPartServiceController = AuditRefundListPart.class.getName();
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
			item.setCode("auditRefund");
			item.setName("审核");
			item.setSeq(2);
			item.setCommand("{controller}.auditRefund();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
	
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("退款审核");
            datagrid.setToolbar (listrowToolbarPath);
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "fefund.soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "fefund.soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "fefund.soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "fefund.soOrder.companyIntention.name", "签单公司", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "fefund.soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "fefund.soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "fefund.soOrder.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn (datagrid, "fefund.soOrder.toBePaidPrice", "待付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        }
        column = addColumn(datagrid, "fefund.amount", "退款金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "fefund.refundType", "退款类别", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "fefund.amount", "==退款业绩分配金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "fefund.createTime", "退款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "fefund.soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "fefund.creator", "退款创建人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "fefund.soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

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
             item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、签单企业");
             item.setWidth(350);
         }
         addQueryItem(queryProject, "fefund.soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX);
         addQueryItem(queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
         addQueryItem(queryProject, "fefund.soOrder.payStatus", "订单付款状态", ControlTypes.ENUM_BOX);
         addQueryItem(queryProject, "fefund.refundType", "退款类别", ControlTypes.ENUM_BOX);
         addQueryItem(queryProject, "fefund.soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX);
         addQueryItem(queryProject, "fefund.creator", "退款业绩创建人", ControlTypes.TEXT_BOX);
         addQueryItem(queryProject, "fefund.createTime", "退款业绩创建时间", ControlTypes.DATE_BOX);
         addQueryItem(queryProject, "fefund.soOrder.createTime", "订单创建时间", ControlTypes.DATE_BOX);
         
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
    }
}
