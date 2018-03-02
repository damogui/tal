package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.TaskAllListPart;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.OrderProdItemDetailPart;
import com.gongsibao.trade.web.SalesmanAddOrderFormPart;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/*全部订单*/
public class SalesmanOrderAllWorkspaceTest extends WorkspaceCreationBase {
    @Override
    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlList = "/crm/order/salesman/all/list";
        urlForm = "/crm/order/salesman/all/form";
        listPartName = formPartName = "全部订单";
        meta = MtableManager.getMtable (entity);
        //formPartName = listPartName = meta.getName ();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
        listToolbarPath = "crm/order/salesman/edit";
//        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/trade/js/salesman-order-all-list.part.js|/gsb/gsb.custom.query.controls.js";
        listPartJsController = SalesmanAllOrderListPart.class.getName ();
//        formJsController = SalesmanAllOrderListPart.class.getName ();
//        formServiceController = SalesmanAllOrderListPart.class.getName ();

        //添加过滤条件
//        listFilter = "inspectionState in (3,4)";
//        listFilter = "foolowStatus = 6 and ownerId = '{userId}'";
    }

    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("所有订单操作");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);
        }
        //详情进行跳转双击操作

        PToolbarItem item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addOrderReceived");
            item.setIcon ("fa fa-plus fa-fw");//fa-user-plus
            item.setName ("创建订单业绩");
            item.setOperationType (ot1);
            item.setSeq (1);
            item.setCommand ("{controller}.addCustomer();");
            toolbar.getItems ().add (item);
        }

        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addReceived");
            item.setIcon ("fa fa-plus fa-fw");
            item.setName ("创建回款业绩");
            item.setOperationType (ot1);
            item.setSeq (2);
            // item.setCommand("{controller}.add();");
            toolbar.getItems ().add (item);
        }

        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addRefund");
            item.setIcon ("fa fa-plus fa-fw");
            item.setName ("创建退款");
            item.setSeq (3);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addStaging");
            item.setIcon ("fa fa-plus fa-fw");
            item.setName ("创建分期");
            item.setSeq (4);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addCarryover");
            item.setIcon ("fa fa-plus fa-fw");
            item.setName ("创建结转");
            item.setSeq (3);
            // item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addContract");
            item.setIcon ("fa fa-plus fa-fw");
            item.setName ("创建合同");
            item.setSeq (5);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("addInvoice");
            item.setIcon ("fa fa-check");
            item.setName ("申请发票");
            item.setSeq (6);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("batchOrderTran");
            item.setIcon ("fa fa-share-square-o");
            item.setName ("批量订单转移");
            item.setSeq (7);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("orderTran");
            item.setIcon ("fa fa-share-square-o");
            item.setName ("订单转移");
            item.setSeq (8);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }
        item = new PToolbarItem ();
        {
            item.toNew ();
            item.setCode ("begOption");
            item.setIcon ("fa fa-check");
            item.setName ("开始操作");
            item.setSeq (9);
            //item.setCommand("{controller}.batchAllocation();");
            toolbar.getItems ().add (item);
        }

        return toolbar;
    }


    /*进行设置工具栏*/
    @Test
    public void saveListToolbar() {

        PToolbar toolbar = createListToolbar ();
        if (toolbar != null) {

            toolbarService.save (toolbar);
        }
    }

    //列表
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("全部订单");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
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
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "addTime", "回款日期", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "办理名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "关联企业", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "refundStatus.name", "退单状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "原价金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "应付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "paidPrice", "已付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "分期付款", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "开发票", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "操作员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "下单人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (6);

        addQueryItem (queryProject, "no", "编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "办理名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "客户创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "owner.name", "下单人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "下单人电话", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "关联企业", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "type", "订单类型", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "产品分类", ControlTypes.TEXT_BOX);

        addQueryItem (queryProject, "no", "下单方式", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "回款日期", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "分期付款", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "开发票", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "创建日期", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        //今天 昨天 本周 本月


        return queryProject;
    }


    // 表单填充字段
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm (node);
        form.setColumnCount (3);

        //String groupName = null;
        String groupName = "基本信息";
//        addFormField (form, "no", "订单", groupName, ControlTypes.TEXT_BOX, true);
        addFormField (form, "no", "订单", groupName, ControlTypes.TEXT_BOX, true);
        addFormField (form, "accountName", "账户名称", groupName, ControlTypes.TEXT_BOX, true);
//        PFormField formField = addFormField(form, "loginName", "帐号", groupName, ControlTypes.TEXT_BOX, false);
//        {
//
//            formField.setReadonly(true);
//            formField.setTooltip("自动生成");
//        }
//        addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false);
//        addFormField(form, "entryDate", "入职日期", groupName, ControlTypes.DATE_BOX, false);
//        addFormField(form, "quitDate", "离职日期", groupName, ControlTypes.DATE_BOX, false);
//        addFormField(form, "disabled", "停用", groupName, ControlTypes.SWITCH_BUTTON, false, true);


        // 这里还有很多属性，

        return form;
    }


//    @Test
//    @Override
//    public void run() {
//        this.createFormWorkspace ();
//    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
        operationService.addOperation (node, OperationTypes.update);
        operationService.addOperation (node, OperationTypes.delete);
    }

}
