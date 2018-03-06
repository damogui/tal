package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanAllOrderFormPart;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;

/*全部订单*/
public class SalesmanOrderAllWorkspaceTest extends WorkspaceCreationBase {
    private String listrowToolbarPath = "/crm/roworderall/toolbar";

    @Override
    @Before
    public void setup() {
        super.setup();
        entity = SoOrder.class;
        urlList = "/crm/order/salesman/all/list";
        urlForm = "/crm/order/salesman/all/form";
        listPartName = formPartName = "全部订单";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
        listToolbarPath = "crm/order/orderall/edit";
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/trade/js/salesman-order-all-list.part.js|/gsb/gsb.custom.query.controls.js";
        listPartJsController = SalesmanAllOrderListPart.class.getName();
        listPartServiceController = SalesmanAllOrderListPart.class.getName();


        List<String> ss = new ArrayList<String>();
        ss.add("/package/easyui/datagrid-cellediting.js");
        ss.add("/package/easyui/datagrid-groupview.js");
        ss.add("/gsb/trade/js/salesman-order-all-form.part.js");
//        ss.add("/gsb/gsb.customer.controls.js");
        formJsImport = StringManager.join("|", ss);
        formJsController = SalesmanAllOrderFormPart.class.getName();
        formServiceController = SalesmanAllOrderFormPart.class.getName();

        //添加过滤条件
//        listFilter = "inspectionState in (3,4)";
//        listFilter = "foolowStatus = 6 and ownerId = '{userId}'";
    }

    @Test
    public void createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.view);//权限挂在哪一个下面
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("所有订单操作");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }


        PToolbarItem item = PToolbarHelper.getPToolbarItem(EntityState.New, "orderDetail", PToolbarHelper.iconExtr,
                "订单详情", ot1, 1, "{controller}.addOrderReceived();");
        toolbar.getItems().add(item);
        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addOrderReceived", PToolbarHelper.iconAdd,
                "创建订单业绩", ot1, 1, "{controller}.addOrderReceived();");
        toolbar.getItems().add(item);
        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addReceived", PToolbarHelper.iconAdd,
                "创建回款业绩", ot1, 2, "{controller}.addReceived();");
        toolbar.getItems().add(item);

        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addRefund", PToolbarHelper.iconAdd,
                "创建退款", ot1, 3, "{controller}.addRefund();");
        toolbar.getItems().add(item);

        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addCarryover", PToolbarHelper.iconAdd,
                "创建结转", ot1, 4, "{controller}.addCarryover();");
        toolbar.getItems().add(item);
        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addStaging", PToolbarHelper.iconAdd,
                "创建分期", ot1, 5, "{controller}.addStaging();");
        toolbar.getItems().add(item);

        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addContract", PToolbarHelper.iconAdd,
                "创建合同", ot1, 6, "{controller}.addContract();");
        toolbar.getItems().add(item);


        item = PToolbarHelper.getPToolbarItem(EntityState.New, "addInvoice", PToolbarHelper.iconCheck,
                "申请发票", ot1, 7, "{controller}.addInvoice();");
        toolbar.getItems().add(item);


        item = PToolbarHelper.getPToolbarItem(EntityState.New, "batchOrderTran", PToolbarHelper.iconTran,
                "批量订单转移", ot1, 8, "{controller}.batchOrderTran();");
        toolbar.getItems().add(item);

        item = PToolbarHelper.getPToolbarItem(EntityState.New, "orderTran", PToolbarHelper.iconTran,
                "订单转移", ot1, 9, "{controller}.orderTran();");
        toolbar.getItems().add(item);
        item = PToolbarHelper.getPToolbarItem(EntityState.New, "begOption", PToolbarHelper.iconCheck,
                "开始操作", ot1, 10, "{controller}.begOption();");
        toolbar.getItems().add(item);


        toolbarService.save(toolbar);
    }


    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        // OperationType ot1 = operationTypeService.byCode(OperationTypes.view);//权限挂在哪一个下面
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setBasePath("panda/datagrid/row/edit");
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("转移");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("ordertran");
            item.setName("转移");
            item.setSeq(2);
            // item.setOperationType (ot1);
            //item.setCommand("{controller}.tran();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }

    //列表
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {

            String toolbarPath = listrowToolbarPath;
            datagrid.setName("全部订单");

            datagrid.setToolbar(toolbarPath);


            datagrid.setAutoQuery(true);
            datagrid.setShowCheckbox(true);
            datagrid.setSingleSelect(false);
//            datagrid.setPageSize(20);
//            datagrid.setShowCheckbox(true);
//            datagrid.setSingleSelect(false);
//            datagrid.setToolbar(rowToolbaPath);
//            datagrid.setReadOnly(true);
//            datagrid.setFilter(listFilter);
//            datagrid.setQueryProject(queryProject);
//            datagrid.setAdvancedQueryProject(advancedQueryProject);
//            datagrid.setToolbar(rowToolbaPath);
        }

        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        column = addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        {
            column.setAlign(DatagridAlign.CENTER);
        }
        addColumn(datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "payStatus", "支付状态", ControlTypes.ENUM_BOX, 60);
        addColumn(datagrid, "companyIntention.companyName", "关联企业", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 80);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 80);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 80);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "payTime", "回款日期", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "refundStatus", "退款状态", ControlTypes.ENUM_BOX, 60);
        addColumn(datagrid, "isInstallment", "是否分期", ControlTypes.BOOLCOMBO_BOX, 80);
        addColumn(datagrid, "customerName", "下单客户", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "accountMobile", "下单客户手机号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "sourceType", "下单方式", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "platformSource", "订单来源", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "addTime", "下单时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "accountType", "新老客户", ControlTypes.ENUM_BOX, 80);

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
            item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、关联公司");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "platformSource", "订单来源", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "accountMobile", "客户手机号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "ywyName", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "isInstallment", "是否分期", ControlTypes.BOOLCOMBO_BOX);
        addQueryItem(queryProject, "addTime", "订单创建日期", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "payTime", "回款日期", ControlTypes.DATE_BOX);
        return queryProject;
    }


    // 表单填充字段
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm(node);
        form.setColumnCount(3);

        //String groupName = null;
        String groupName = "基本信息";
        addFormField(form, "no", "订单", groupName, ControlTypes.TEXT_BOX, true);
        addFormField(form, "accountName", "账户名称", groupName, ControlTypes.TEXT_BOX, true);
        // 这里还有很多属性，

        return form;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);

//       operationService.addOperation (node, OperationTypes.add);
//        operationService.addOperation (node, OperationTypes.update);
//        operationService.addOperation (node, OperationTypes.delete);
    }

}
