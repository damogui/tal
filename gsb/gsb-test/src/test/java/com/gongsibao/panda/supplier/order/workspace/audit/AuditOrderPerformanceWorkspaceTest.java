package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
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
/*订单业绩审核*/
public class AuditOrderPerformanceWorkspaceTest extends WorkspaceCreationBase {
    private String listrowToolbarPath="/crm/roworderper/toolbar";
    @Before
    public void setup() {
        super.setup ();
        entity = NDepReceivable.class;
        urlList = "/crm/order/audit/orderp/list";
        listPartName = formPartName = "订单业绩审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Performance";
        listToolbarPath = "";//crm/audit/orderp/edit
        listPartImportJs = "/gsb/platform/trade/js/audit-order-performance.js|/gsb/panda-extend/gsb.custom.query.controls.js";

    }

    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("审核");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("view");
            item.setName("审核");
            item.setSeq(1);
            //item.setCommand("{controller}.view();");
            toolbar.getItems().add(item);
        }


        toolbarService.save(toolbar);
    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setToolbar (listrowToolbarPath);
            datagrid.setName ("订单业绩审核");

            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "order.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "order.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "order.prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "order.companyIntention.name", "签单公司", ControlTypes.TEXT_BOX, 100);

        addColumn (datagrid, "order.totalPrice", "原价金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "order.payablePrice", "应付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "order.paidPrice", "已付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "orderId", "待付款金额", ControlTypes.TEXT_BOX, 100);//??
        addColumn (datagrid, "order.payStatus", "待付款状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "amount", "订单业界分配金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "statusType", "审核状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "createTime", "订单业绩创建款时间", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "order.createTime", "订单创建时间", ControlTypes.TEXT_BOX, 100);

        addColumn (datagrid, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "order.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (3);
        PQueryItem item = null;
        item = addQueryItem (queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip ("订单编号、渠道订单编号、下单人、下单人电话、关联公司");
            item.setWidth (350);
        }

        addQueryItem (queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "statusType", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "order.payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "order.owner.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX);

        addQueryItem (queryProject, "createTime", "订单业绩创建时间", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "order.createTime", "订单创建时间", ControlTypes.TEXT_BOX);


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
