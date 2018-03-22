package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.AuditStageListPart;

/*分期订单*/
public class SalesmanOrderStagingWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = SoOrder.class;
        urlList = "/crm/order/salesman/staging/list";
        listPartName = formPartName = "我的分期";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Staging";
        /*listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartServiceController = SalesmanStagingListPart.class.getName();*/
        listFilter = " owner_id = '{userId}' and is_installment = 1 ";
        
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/audit-stage-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditStageListPart.class.getName();
		listPartServiceController = AuditStageListPart.class.getName();
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("我的分期");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn(datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "companyIntention.name", "签单公司", ControlTypes.TEXT_BOX, 250);
        column = addColumn(datagrid, "totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column =  addColumn(datagrid, "paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        	column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn (datagrid, "toBePaidPrice", "待付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        }
        addColumn(datagrid, "stageNum", "分期次数", ControlTypes.ENUM_BOX, 100);
        
        addColumn(datagrid, "installmentAuditStatusId", "审核状态", ControlTypes.ENUM_BOX, 100);
        
        
//        addColumn(datagrid, "stageCreateTime", "分期申请时间", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "createTime", "订单创建时间", ControlTypes.DATE_BOX, 350);
        addColumn(datagrid, "creator", "分期申请人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

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
            item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、签单企业");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "installmentAuditStatusId", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "owner.name", "业务员", ControlTypes.TEXT_BOX);
//        addQueryItem(queryProject, "stageCreator", "分期申请人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "stageNum", "分期次数", ControlTypes.ENUM_BOX);
//        addQueryItem(queryProject, "stageCreateTime", "分期申请时间", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "createTime", "订单创建时间", ControlTypes.DATE_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }

}
