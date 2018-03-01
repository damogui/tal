package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.OrderProdItemDetailPart;
import com.gongsibao.trade.web.SalesmanAddOrderFormPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/*全部订单*/
public class SalesmanOrderAllWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlForm = "/crm/order/salesman/all/list";
        listPartName = formPartName = "全部订单";
        meta = MtableManager.getMtable (entity);
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";

        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/crm/sys/js/sys-salesman-list-part.js|/gsb/gsb.custom.query.controls.js";
//        List<String> ss = new ArrayList<String> ();
//        ss.add("/gsb/trade/js/salesman-order-add-form.part.js");
//        ss.add("/gsb/gsb.customer.controls.js");
//        formJsImport = StringManager.join("|", ss);
//        formJsController = SalesmanAddOrderFormPart.class.getName();
//        formServiceController = SalesmanAddOrderFormPart.class.getName();
    }
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("全部订单");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "supplier.name", "姓名", ControlTypes.TEXT_BOX, 80);
       addColumn(datagrid, "department.name", "帐号", ControlTypes.TEXT_BOX, 100);
//        column = addColumn(datagrid, "disabled", "状态", ControlTypes.TEXT_BOX, 80);
//        {
//            column.setAlign(DatagridAlign.CENTER);
//            column.setFormatter("return controllerdepartments.disabledFormatter(value,row,index);");
//        }
//        column = addColumn(datagrid, "receiving", "接单", ControlTypes.TEXT_BOX, 80);
//        {
//            column.setAlign(DatagridAlign.CENTER);
//            column.setFormatter("return controllerdepartments.receivingFormatter(value,row,index);");
//        }
//        addColumn(datagrid, "department.name", "部门", ControlTypes.TEXT_BOX, 120);
//        column = addColumn(datagrid, "employee.loginNum", "登录次数", ControlTypes.TEXT_BOX, 100);
//        {
//            column.setAlign(DatagridAlign.CENTER);
//        }

//        addColumn(datagrid, "isLeader", "主管", ControlTypes.BOOLCOMBO_BOX, 80);
//        addColumn(datagrid, "dayMax", "日分配上限", ControlTypes.TEXT_BOX, 80);
//        addColumn(datagrid, "weekMax", "周分配上限", ControlTypes.TEXT_BOX, 80);
//        addColumn(datagrid, "xabMax", "XAB类上限", ControlTypes.TEXT_BOX, 80);
//        addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
//        addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
//        addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
//        addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130);

        return datagrid;
    }
    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node, OperationTypes.add);
        operationService.addOperation(node, OperationTypes.update);
        operationService.addOperation(node, OperationTypes.delete);
    }

}
