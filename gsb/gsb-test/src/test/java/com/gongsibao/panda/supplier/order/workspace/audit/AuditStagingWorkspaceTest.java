package com.gongsibao.panda.supplier.order.workspace.audit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.SalesmanStagingListPart;

/*分期审核*/
public class AuditStagingWorkspaceTest extends WorkspaceCreationBase{
	protected String listrowToolbarPath = "/audit/rowStag/toolbar";
	@Override
	@Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlList = "/crm/order/audit/staging/list";
        listPartName = formPartName = "分期审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Staging";
        listFilter = "is_installment = 1";
        
        List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/salesman-order-stage-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = SalesmanStagingListPart.class.getName();
		listPartServiceController = SalesmanStagingListPart.class.getName();
    }

    @Test
	public void createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listrowToolbarPath);
			toolbar.setName("审核");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("auditStage");
			item.setName("审核");
			item.setSeq(2);
			item.setCommand("{controller}.auditStage();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
    
    
    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("分期审核");
            datagrid.setToolbar (listrowToolbarPath);
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 200);
        addColumn (datagrid, "companyIntention.name", "签单公司", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "原价金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payablePrice", "应付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "paidPrice", "已付金额", ControlTypes.TEXT_BOX, 100);
        column = addColumn (datagrid, "toBePaidPrice", "待付金额", ControlTypes.DECIMAL_FEN_BOX, 100);{
        }
        addColumn (datagrid, "stageNum", "分期次数", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "installmentAuditStatusId", "审核状态", ControlTypes.ENUM_BOX, 100);
        column = addColumn (datagrid, "stages", "==分期申请时间==", ControlTypes.DATE_BOX, 350);
        {     
        	column.setAlign(DatagridAlign.CENTER);
        	column.setFormatter("return controllersoOrderList.serviceNameFormatter(value,row,index);");
        }
        addColumn (datagrid, "createTime", "订单创建时间", ControlTypes.DATE_BOX, 350);
        addColumn (datagrid, "creator", "分期申请人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
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
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
        operationService.addOperation (node, OperationTypes.update);
        operationService.addOperation (node, OperationTypes.delete);
    }

}
