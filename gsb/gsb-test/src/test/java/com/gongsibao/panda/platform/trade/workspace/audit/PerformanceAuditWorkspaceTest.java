package com.gongsibao.panda.platform.trade.workspace.audit;

import com.gongsibao.panda.supplier.order.workspace.audit.AuditOrderPerformanceWorkspaceTest;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.AuditLog;
/*交易中心订单业绩审核*/
public class PerformanceAuditWorkspaceTest  extends AuditOrderPerformanceWorkspaceTest {
	@Before
	public void setup() {

		//entity = AuditLog.class;// 实体
		urlList = "/trade/audit/performance/list";// 列表的url
		//urlForm = "/trade/audit/performance/form";// 弹出框的url
		listPartName = formPartName = "交易中心订单业绩审核";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Audit_Performance";// 菜单节点码（名称）
//
//		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
//		openWindowHeight = 400;
//		openWindowWidth = 800;
//		listFilter = "type_id=1042 AND add_user_id='{userId}' ";

		/*listPartServiceController = OrderOperationController.class.getName();
		listPartJsController = OrderOperationController.class.getName();
		listPartImportJs = "/gsb/platform/trade/js/orderoperation.list.part.js";
		listToolbarPath = "/trade/manage/order/operation/toolbar";*/
	}



}
