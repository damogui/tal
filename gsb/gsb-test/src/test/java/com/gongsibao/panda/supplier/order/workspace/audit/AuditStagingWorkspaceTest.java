package com.gongsibao.panda.supplier.order.workspace.audit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.AuditStagingListPart;

/*分期审核*/
public class AuditStagingWorkspaceTest extends WorkspaceCreationBase{
	
	@Override
	@Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/staging/list";
        listPartName = formPartName = "分期审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Staging";
        
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/audit/audit-stage-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditStagingListPart.class.getName();
		listPartServiceController = AuditStagingListPart.class.getName();
		
		listFilter = "type_id=" + AuditLogType.Fqsq.getValue()+ " AND add_user_id='{userId}' ";
    }
    
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("分期审核");
            datagrid.setToolbar ("");
            datagrid.setAutoQuery (true);
        }
        PDatagridColumn column = null;
        column = addColumn (datagrid, "id", "操作", ControlTypes.TEXT_BOX, 100, true);{
        	column.setAlign(DatagridAlign.CENTER);
        	column.setFormatter("return controllerauditLogList.operateFormatter(value,row,index)");
        }
        column = addColumn(datagrid, "formId", "来源Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setVisible(false);
        }
        column = addColumn(datagrid, "soOrder.id", "订单Id", ControlTypes.NUMBER_BOX, 100, true);{
        	column.setSystem(true);
        	column.setVisible(false);
        }
        addColumn (datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 200);
        addColumn (datagrid, "soOrder.companyIntention.name", "签单公司", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.paidPrice", "已付金额", ControlTypes.TEXT_BOX, 100);
        column = addColumn (datagrid, "soOrder.toBePaidPrice", "待付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        }
        addColumn (datagrid, "soOrder.stageNum", "分期次数", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        column = addColumn (datagrid, "soOrder.stageCreateTime", "分期申请时间", ControlTypes.DATETIME_BOX, 350);
        {     
        	column.setAlign(DatagridAlign.CENTER);
        	column.setOrderbyMode (OrderbyMode.DESC);
        }
        addColumn (datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 350);
        addColumn (datagrid, "soOrder.stageCreator", "分期申请人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

    	PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        PQueryItem item = null;
        queryProject.setColumnCount(3);

        /*item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、签单企业");
            item.setWidth(350);
        }*/
        /*addQueryItem(queryProject, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX);*/
        addQueryItem(queryProject, "soOrder.installmentAuditStatusId", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.payStatus", "付款状态", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "soOrder.stageCreator", "分期申请人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "soOrder.stageNum", "分期次数", ControlTypes.ENUM_BOX);
        addQueryItem(queryProject, "soOrder.stageCreateTime", "分期申请时间", ControlTypes.DATE_BOX);
        /*addQueryItem(queryProject, "soOrder.createTime", "订单创建时间", ControlTypes.DATE_BOX);*/
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
    }

}
