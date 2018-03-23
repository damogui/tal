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
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.department.DepartmentOrderAllListPart;

/*全部订单   根据部门过滤就行*/
public class DepartmentOrderAllWorkspaceTest extends SalesmanOrderAllWorkspaceTest {

    private String listrowToolbarPath = "/department/roworderall/toolbar";

    @Before
    public void setup() {

        super.setup();
        urlList = "/crm/order/department/all/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_All";
        
        //批量转移工具栏
        listToolbarPath = "department/order/orderall/edit";
        listPartServiceController = DepartmentOrderAllListPart.class.getName();
        listFilter = "";
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

		ResourceNode node = resourceService.byCode(resourceNodeCode);
//		OperationType ot1 = operationTypeService.byCode(OperationTypes.view);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("所有订单操作");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("batchOrderTran");
			item.setIcon(PToolbarHelper.iconTran);
			item.setName("批量转移");
			item.setSeq(1);
			item.setCommand("{controller}.batchOrderTran();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
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
