package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;

/*全部订单   根据部门过滤就行*/
public class DepartmentOrderAllWorkspaceTest extends SalesmanOrderAllWorkspaceTest {

    private String listrowToolbarPath = "/department/roworderall/toolbar";

    @Before
    public void setup() {

        super.setup();

        //entity = SoOrder.class;// 实体
        urlList = "/crm/order/department/all/list";// 列表的url
        resourceNodeCode = "Gsb_Supplier_Order_Department_All";
        listToolbarPath = "department/order/orderall/edit";
        listPartImportJs = "/gsb/platform/trade/js/salesman-order-all-list.part.js||/gsb/panda-extend/gsb.custom.query.controls.js|/gsb/panda-extend/gsb.pubcontrol.js";
        listPartJsController = SalesmanAllOrderListPart.class.getName();
        listPartServiceController = SalesmanAllOrderListPart.class.getName();
        listFilter = "";//为了测试有数据，所以先条件为空，之后正式用下面的
        //listFilter = " department_id in (select department_id from sp_salesman where employee_id = '{userId}' ) ";

        //listPartName = formPartName = "全部订单";
        //meta = MtableManager.getMtable(entity);// 获取实体元数据
        //formPartName = listPartName = meta.getName();
        //listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/platform/operation/crm/js/customer-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        //listPartJsController = NCustomerAllListPart.class.getName();
        //listPartServiceController = NCustomerAllListPart.class.getName();
    }

    @Test
    public void createRowToolbar() {
        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
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
            item.setCode("orderTran");
            item.setName("转移");
            item.setSeq(2);
            item.setCommand("{controller}.orderTran();");
            toolbar.getItems().add(item);
        }
        toolbarService.save(toolbar);
    }
    @Test
    public void createListToolbar() {
    	
    }

    protected PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid grid = super.createDatagrid(node);
        grid.setToolbar(listrowToolbarPath);
        return grid;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}
