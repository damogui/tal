package com.gongsibao.panda.supplier.order.workspace.department;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPayWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderPayListPart;
import com.gongsibao.trade.web.department.DepartmentOrderReceivedListPart;
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
 * Created by win on 2018/3/22.
 */
/*部门回款*/
public class DepartmentOrderPayWorkspaceTest extends SalesmanOrderPayWorkspaceTest {

    private String listrowToolbarPath = "/crm/department/roworderpay/toolbar";
    @Before
    public void setup() {
        super.setup();
        entity = Pay.class;
        urlList = "/crm/order/department/pay/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Pay";
        listPartName = formPartName = "部门回款";//我部门创建的回款和别人创建的回款的订单所有人在我的部门
        meta = MtableManager.getMtable (entity);
        //listPartImportJs = "/gsb/platform/trade/js/salesman-order-payperformance-list.js";
        //listFilter = "salesman_id = '{userId}' ";//and deId in(select deid from sp_salesman where empyleId='{}')

        listToolbarPath="";
        //listFilter = " pkid IN (SELECT pay_id FROM so_order_pay_map WHERE order_id IN (SELECT pkid FROM so_order WHERE owner_id = '{userId}' ORDER BY pkid DESC)) OR add_user_id = '{userId}' ";
        // listPartJsController = SalesmanOrderReceivedListPart.class.getName ();
        listFilter="";
        listPartServiceController = DepartmentOrderPayListPart.class.getName();


    }


    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("部门回款查看");
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
