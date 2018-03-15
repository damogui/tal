package com.gongsibao.panda.supplier.order.workspace.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.tools.PToolbarHelper;

/*合同管理*/
public class SalesmanOrderContractWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {
        super.setup ();
        entity = Contract.class;
        urlList = "/crm/order/salesman/contract/list";
        listPartName = formPartName = "合同管理";
        meta = MtableManager.getMtable (entity);
        formPartName = listPartName = meta.getName ();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Contract";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";///gsb/crm/sys/js/sys-salesman-list-part.js|

//        List<String> ss = new ArrayList<String> ();
//        ss.add("/gsb/platform/trade/js/salesman-order-add-form.part.js");
//        ss.add("/gsb/panda-extend/gsb.customer.controls.js");
//        formJsImport = StringManager.join("|", ss);
//        formJsController = SalesmanAddOrderFormPart.class.getName();
//        formServiceController = SalesmanAddOrderFormPart.class.getName();
    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("合同管理");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.accountType", "新老客户签单", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.payablePrice", "合同总额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "contractPrice", "业绩总额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "dataFee", "材料撰写费", ControlTypes.TEXT_BOX, 100);

        addColumn (datagrid, "liquidatedDamages", "违约金", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "creator", "合同创建人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "createTime", "合同创建时间", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "auditStatusId", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);


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
        addQueryItem(queryProject, "auditStatus", "产品名称", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "creator", "审核状态", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "合同创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "createTime", "合同创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
    }

}
