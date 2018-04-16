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

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.trade.web.OrderSalesmanRefundListPart;

/*退款订单*/
public class SalesmanOrderRefundWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = Refund.class;
        urlList = "/crm/order/salesman/refund/list";
        listPartName = formPartName = "我的退款";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Refund";
        
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/salesman-order-refund-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = OrderSalesmanRefundListPart.class.getName();
        listPartServiceController = OrderSalesmanRefundListPart.class.getName();
        //过滤条件：我退的款或者退款业绩分给我的
        listFilter = "(Refund.add_user_id = '{userId}' OR Refund.pkid in(SELECT DISTINCT refund_id from n_dep_refund where salesman_id = '{userId}'))";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("退款订单");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        column = addColumn(datagrid, "orderId", "订单Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setSystem(true);
        	column.setVisible(false);
        }
        addColumn(datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "soOrder.companyIntention.name", "签单公司", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "soOrder.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn (datagrid, "soOrder.toBePaidPrice", "待付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "amount", "退款金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        	column.setFormatter("return (value/100).toFixed(2);");
        }
        addColumn(datagrid, "refundType", "退款类别", ControlTypes.ENUM_BOX, 100);
        //退款业绩额表中计算出来
        /*column = addColumn(datagrid, "amount", "我的退款业绩额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	
        	column.setAlign(DatagridAlign.RIGHT);
        }*/
        addColumn(datagrid, "auditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "createTime", "退款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "updateTime", "退款审核通过时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "退款创建人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

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
            item.setTooltip("订单编号、渠道订单编号、签单公司");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "auditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.payStatus", "订单付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "refundType", "退款类别", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "退款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "createTime", "退款业绩创建时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "soOrder.createTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
