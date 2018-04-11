package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.web.AuditPayListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * 回款审核.
 */
public class AuditPayWorkspaceTest  extends WorkspaceCreationBase {

    //private String listrowToolbarPath="/crm/order/audit/roworderpay/toolbar";
    @Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/pay/list";
        listPartName = formPartName = "回款审核";//回款审核
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Pay_Audit";
        listToolbarPath = "";
        listPartImportJs = "/gsb/platform/trade/js/audit/audit-pay-list.js";
        listPartJsController = AuditPayListPart.class.getName ();
        listPartServiceController = AuditPayListPart.class.getName ();

        listFilter = "type_id=" + AuditLogType.Sksq.getValue() + " and add_user_id = '{userId}' ";

    }





//    @Test
//    public void createRowToolbar() {
//
//        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
//        PToolbar toolbar = new PToolbar();
//        {
//            toolbar.toNew();
//            toolbar.setPath(listrowToolbarPath);
//            toolbar.setName("审核");
//            toolbar.setResourceNode(node);
//            toolbar.setToolbarType(ToolbarType.BASE);
//        }
//        PToolbarItem item = new PToolbarItem();
//        {
//            item.toNew();
//            item.setCode("audit");
//            item.setName("审核");
//
//            item.setSeq(1);
//            item.setCommand("{controller}.audit();");
//            toolbar.getItems().add(item);
//        }
//
//
//        toolbarService.save(toolbar);
//    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            //datagrid.setToolbar (listrowToolbarPath);
            datagrid.setName ("回款业绩审核");

            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (true);
        }
        PDatagridColumn column = null;
        column=addColumn (datagrid, "id", "操作", ControlTypes.TEXT_BOX, 60, true);{
            //column.setFormatter ("return   <a>新加审核");
            column.setFormatter("return controllerauditLogList.optionFormatter(value,row,index);");

        }
        // addColumn (datagrid, "u8Bank.name", "姓名", ControlTypes.TEXT_BOX, 100);
        column = addColumn (datagrid, "pay.id", "支付id", ControlTypes.TEXT_BOX, 120);//需要拼接
        column = addColumn (datagrid, "pay.orderNo", "订单编号", ControlTypes.TEXT_BOX, 120);//需要拼接
        {
            // column.setFormatter("return controllerauditLogList.orderNameFormatter(value,row,index);");

        }
        addColumn (datagrid, "pay.payForOrderCount", "是否一笔多单", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "pay.payWayType", "是否在线支付", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "pay.amount", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "pay.confirmTime", "回款日期", ControlTypes.DATE_BOX, 100);
        addColumn (datagrid, "pay.createTime", "回款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn (datagrid, "pay.creator", "回款业绩创建人", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 60);{

            column.setSystem (true);
            column.setVisible (false);
            column.setOrderbyMode (OrderbyMode.DESC);


        }


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
            item.setTooltip ("订单编号");
            item.setWidth (350);
        }


        addQueryItem (queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "pay.payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "pay.payWayType", "是否在线支付", ControlTypes.ENUM_BOX);

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
