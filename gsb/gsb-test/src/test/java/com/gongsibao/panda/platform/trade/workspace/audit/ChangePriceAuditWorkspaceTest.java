package com.gongsibao.panda.platform.trade.workspace.audit;
import org.junit.Before;
import org.junit.Test;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditOrderWorkspaceTest;

/**   
 * @ClassName:  ChangePriceAuditWorkspaceTest   
 * @Description:TODO 改价审核
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:09:45   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ChangePriceAuditWorkspaceTest extends AuditOrderWorkspaceTest {

	@Before
	public void setup() {
		super.setup();
		urlList = "/trade/audit/change/list";
		resourceNodeCode = "GSB_Trade_Audit_Price_Change";		
		listFilter = "type_id=" + AuditLogType.Ddgj.getValue()+ " AND add_user_id='{userId}' ";
	}
	
    @Test
	public void createRowToolbar() {
    	
    }
    
	/*hw写的代码类继承的是  extends WorkspaceCreationBase  @Before
	public void setup() {

		entity = AuditLog.class;// 实体
		urlList = "/trade/audit/change/list";// 列表的url
		urlForm = "/trade/audit/change/form";// 弹出框的url
		listPartName = formPartName = "改价审核列表";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Audit_Price_Change";// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowHeight = 400;
		openWindowWidth = 800;
		listFilter = "type_id=1042 AND add_user_id='{userId}' ";

		listPartServiceController = OrderOperationController.class.getName();
		listPartJsController = OrderOperationController.class.getName();
		listPartImportJs = "/gsb/platform/trade/js/orderoperation.list.part.js";
		listToolbarPath = "/trade/manage/order/operation/toolbar";
	}

	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("订单操作工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "disabled", "批量转移", "fa fa-edit", "batchTransferWeb()", null, 5);
		toolbarService.save(toolbar);
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("改价审核列表");
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "审核id", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
		}
		//注释掉的列暂时出不来
		addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "soOrder.prodName", "产品名称", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 80);
		addColumn(datagrid, "soOrder.payablePrice", "订单金额", ControlTypes.DECIMAL_FEN_BOX, 80);
		addColumn(datagrid, "soOrder.paidPrice", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 80);		
		addColumn(datagrid, "", "业务员", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "", "申请人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "", "申请时间", ControlTypes.DATETIME_BOX, 100);		
		addColumn(datagrid, "soOrder.accountName", "下单人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "soOrder.accountMobile", "下单人电话", ControlTypes.TEXT_BOX, 100);
		//addColumn(datagrid, "", "关联企业", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "soOrder.createTime", "下单时间", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "soOrder.sourceType.name", "下单方式", ControlTypes.TEXT_BOX, 100);		
		addColumn(datagrid, "statusId", "审核状态", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "soOrder.accountType", "新老客户签单", ControlTypes.ENUM_BOX, 80);

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "operator", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "oldOperator", "原业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "customerName", "下单人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "accountMobile", "下单人手机号", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {
		PForm form = super.createForm(node);
		form.setColumnCount(3);
		PFormField field = null;
		addFormField(form, "orderNo", "订单号", ControlTypes.TEXT_BOX, true, false);
		return form;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		
		 * operationService.addOperation(node,OperationTypes.add);
		 * operationService.addOperation(node,OperationTypes.update);
		 * operationService.addOperation(node,OperationTypes.delete);
		 
	}*/
}
