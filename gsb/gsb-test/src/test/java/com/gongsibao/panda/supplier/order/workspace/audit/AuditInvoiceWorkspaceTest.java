package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.AuditContractListPart;
import com.gongsibao.trade.web.AuditInvoiceListPart;
import com.gongsibao.trade.web.OrderSalesmanRefundListPart;
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

/**
 * Created by zhangchao on 2018/3/23.
 */
public class AuditInvoiceWorkspaceTest extends WorkspaceCreationBase {

    private String listrowToolbarPath = "/audit/rowinvoice/toolbar";

    @Before
    public void setup() {
        super.setup();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/invoice/list";
        listPartName = formPartName = "发票审核";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Invoice";
        listPartImportJs = "/gsb/platform/trade/js/audit-invoice.ctrl.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listToolbarPath = "crm/audit/orderinvoice/edit";
        listPartServiceController = AuditInvoiceListPart.class.getName();
        listPartJsController = AuditInvoiceListPart.class.getName();
        listFilter = "type_id=" + AuditLogType.Fbsq.getValue() + " and add_user_id = '{userId}' ";
    }

    @Test
    public void createListToolbar() {
        ResourceNode node = resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("审核");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("addAudit");
            item.setIcon(PToolbarHelper.iconCheck);
            item.setName("审核");
            item.setSeq(2);
            item.setCommand("{controller}.addAudit();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
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
            item.setCode("addAudit");
            item.setName("审核");
            item.setSeq(2);
            item.setCommand("{controller}.addAudit();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("合同审核");
            datagrid.setToolbar(listrowToolbarPath);
            datagrid.setAutoQuery(true);
            datagrid.setShowCheckbox(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        column = addColumn(datagrid, "formId", "formId", ControlTypes.OPERATION_COLUMN, 100, true);
        {
            column.setVisible(false);
        }
        addColumn(datagrid, "invoice.soOrderNo", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "invoice.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.accountTypeName", "新老客户签单", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.orderPayablePrice", "订单金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "invoice.orderPaidPrice", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "invoice.amount", "发票金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "invoice.typeId", "发票类型", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "invoice.title", "发票抬头", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.companyId", "开票公司", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "invoice.createTime", "发票申请时间", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "invoice.creator", "发票申请人", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "status", "审核状态", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "invoice.orderCreateTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "invoice.salesman.name", "业务员", ControlTypes.TEXT_BOX, 100);

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
        addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "invoice.typeId", "发票类型", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "invoice.salesman.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "invoice.creator", "发票申请人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "invoice.createTime", "发票申请时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "orderCreateTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}