package com.gongsibao.panda.crm.workspace.sys;

import com.gongsibao.crm.web.CustomerMyOrderListPart;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.panda.trade.workspace.order.AllOrderWorkspaceTest;
import org.junit.Before;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by zhangchao on 2018/2/9.
 */
public class CustomerMyOrderWorkspaceTest extends AllOrderWorkspaceTest {

    @Override
    @Before
    public void setup() {

        super.setup();

        listPartName = "客户订单";
        resourceNodeCode = "CRM_My_All_"+SoOrder.class.getSimpleName();
        urlList = "/crm/customer/my/order/list";
        urlForm = "/crm/customer/my/order/form";

        listPartServiceController = CustomerMyOrderListPart.class.getName();
        listPartJsController = CustomerMyOrderListPart.class.getName();
        listPartImportJs = "/gsb/crm/js/customer.myorder.list.part.js";

    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        datagrid.setAutoQuery(false);//默认不加载

        return datagrid;
    }


    protected PQueryProject createQueryProject(ResourceNode node) {
        //PQueryProject queryProject = super.createQueryProject(node);
        //queryProject.toNew();
        /*addQueryItem(queryProject, "accountName", "客户名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "prodName", "服务名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "payTime", "订单支付时间", ControlTypes.DATE_BOX);*/
        return new PQueryProject();
    }


    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
