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
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.AuditStageListPart;

/*分期审核*/
public class AuditStagingWorkspaceTest extends WorkspaceCreationBase{
	private String listrowToolbarPath = "/audit/rowStag/toolbar";

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
		ss.add("/gsb/platform/trade/js/audit-stage-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = AuditStageListPart.class.getName();
		listPartServiceController = AuditStageListPart.class.getName();
    }

   /* public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        // OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("订单业绩");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);
        }


        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconAdd,
                "查看审核记录", null, 1, "{controller}.add();");
        toolbar.getItems ().add (item);
        item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconCheck,
                "审核", null, 2, "{controller}.add();");
        toolbar.getItems ().add (item);
        return toolbar;
    }



    进行设置工具栏
    @Test
    public void saveListToolbar() {

        PToolbar toolbar = createListToolbar ();
        if (toolbar != null) {

            toolbarService.save (toolbar);
        }
    }*/
    
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
        column = addColumn (datagrid, "toBePaid", "待付金额", ControlTypes.TEXT_BOX, 100);{
        	column.setFormatter("return (row.payablePrice - row.paidPrice)");
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

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (6);

        addQueryItem (queryProject, "no", "编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "办理名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "客户创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "owner.name", "下单人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "下单人电话", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "关联企业", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "type", "订单类型", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "产品分类", ControlTypes.TEXT_BOX);

        addQueryItem (queryProject, "no", "下单方式", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "回款日期", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "分期付款", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "no", "开发票", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "创建日期", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        //今天 昨天 本周 本月




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
