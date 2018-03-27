package com.gongsibao.panda.supplier.order.workspace.department;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.web.AuditPayListPart;
import com.gongsibao.trade.web.SalesmanOrderReceivedListPart;
import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderReceivedListPart;
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

/*部门回款业绩*/
public class DepartmentOrderReceivedWorkspaceTest extends SalesmanOrderReceivedWorkspaceTest {

    private String listrowToolbarPath = "/crm/department/roworderre/toolbar";
    @Before
    public void setup() {
        super.setup();
        entity = NDepPay.class;
        urlList = "/crm/order/department/received/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Received";
        listPartName = formPartName = "部门回款业绩列表";//回款业绩  我部门创建的回款业绩和别人创建的回款业绩的订单所有人在我的部门
        meta = MtableManager.getMtable (entity);
        listToolbarPath="";
       // listFilter = "salesman_id = '{userId}'  or creator_id = '{userId}'";//我创建和别人分配给我
        listPartServiceController = DepartmentOrderReceivedListPart.class.getName();


    }


    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("部门业绩回款查看");
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


}
