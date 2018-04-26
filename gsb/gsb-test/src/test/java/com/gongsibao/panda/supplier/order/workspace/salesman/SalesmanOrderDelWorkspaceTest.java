package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/*无效订单*/
public class SalesmanOrderDelWorkspaceTest extends WorkspaceCreationBase {

    private String listrowToolbarPath = "/crm/roworderdel/toolbar";

    @Override
    @Before
    public void setup() {
        super.setup();
        entity = SoOrder.class;
        urlList = "/crm/order/salesman/del/list";
        listPartName = "无效订单";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Del";
        listToolbarPath = "crm/order/del/list";


        List<String> ss = new ArrayList<String>();
        ss.add("/gsb/layerutil/layerresize.js");//弹窗兼容性
        ss.add("/gsb/platform/trade/js/salesman-order-all-list.part.js");
        ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
        ss.add("/gsb/panda-extend/gsb.pubcontrol.js");
        listPartImportJs = StringManager.join("|", ss);

        listFilter = " (owner_id = '{userId}'  or  add_user_id='{userId}') and (is_delete=1 or process_status_id=3023)";
        listPartJsController = SalesmanAllOrderListPart.class.getName();
        listPartServiceController = SalesmanAllOrderListPart.class.getName();
    }

    @Test
    public void createListToolbar() {

        String listToolbarPath = "crm/order/del/list";
        ResourceNode node = resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.view);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("所有订单操作");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
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
            toolbar.setName("转移");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("detail");
            item.setName("查看");
            item.setSeq(1);
            item.setCommand("{controller}.detail();");
            toolbar.getItems().add(item);
        }


        toolbarService.save(toolbar);
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {

            String toolbarPath = listrowToolbarPath;
            datagrid.setName("全部订单");
            datagrid.setToolbar(toolbarPath);
            datagrid.setShowCheckbox(true);
            datagrid.setSingleSelect(false);
        }

        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        column = addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        {
            column.setAlign(DatagridAlign.CENTER);

        }
        addColumn(datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 250);
        addColumn(datagrid, "payStatus", "付款状态", ControlTypes.ENUM_BOX, 60);
        addColumn(datagrid, "companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 250);
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
        column = addColumn(datagrid, "balance", "余额", ControlTypes.DECIMAL_FEN_BOX, 80);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "fistPayTime", "首款审核日期", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "changePriceAuditStatus", "审核状态", ControlTypes.ENUM_BOX, 60);
        addColumn(datagrid, "isInstallment", "分期付款", ControlTypes.BOOLCOMBO_BOX, 80);
        addColumn(datagrid, "customerName", "下单人", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "accountMobile", "下单人电话", ControlTypes.TEXT_BOX, 100);
        {

            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'手机号\\')');");

        }
        column = addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 80);{

            column.setAlign(DatagridAlign.CENTER);
        }

        addColumn(datagrid, "sourceType", "下单方式", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "platformSource", "订单来源", ControlTypes.ENUM_BOX, 80);
        column = addColumn(datagrid, "createTime", "下单时间", ControlTypes.DATETIME_BOX, 100);{

            column.setOrderbyMode(OrderbyMode.DESC);
        }
        addColumn(datagrid, "accountType", "新老客户", ControlTypes.ENUM_BOX, 80);
        column = addColumn(datagrid, "ownerId", "业务员id", ControlTypes.TEXT_BOX, 80);
        {
            column.setVisible(false);
        }
        column = addColumn(datagrid, "supplierId", "服务商id", ControlTypes.TEXT_BOX, 80);
        {
            column.setVisible(false);
        }
        column = addColumn(datagrid, "departmentId", "部门id", ControlTypes.TEXT_BOX, 80);
        {
            column.setVisible(false);
        }
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
            item.setTooltip("订单编号、渠道订单编号、下单人电话、签单公司");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "platformSource", "订单来源", ControlTypes.ENUM_BOX);
       // addQueryItem(queryProject, "payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "sourceType", "下单方式", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "ywyName", "业务员", ControlTypes.TEXT_BOX);
        //addQueryItem(queryProject, "isInstallment", "分期付款", ControlTypes.BOOLCOMBO_BOX);
        //addQueryItem(queryProject, "fistPayTime", "首款审核日期", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "createTime", "创建日期", ControlTypes.DATE_BOX);

        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
