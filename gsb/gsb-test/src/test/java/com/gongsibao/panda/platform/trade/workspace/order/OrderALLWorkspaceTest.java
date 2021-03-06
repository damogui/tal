package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.platform.AllOrderListPart;

/**
 * Created by zhangchao on 2018/3/9.
 */
/*全部订单*/
public class OrderALLWorkspaceTest extends SalesmanOrderAllWorkspaceTest {

	private String listrowToolbarPath = "/operation/roworderall/toolbar";

	@Before
	public void setup() {

		super.setup();

		// entity = SoOrder.class;// 实体
		urlList = "/operation/order/all/list";// 列表的url
		resourceNodeCode = "Operation_Order_All";
		listToolbarPath = "operation/order/orderall/edit";
		listPartImportJs = "/gsb/platform/trade/js/salesman-order-all-list.part.js|/gsb/platform/trade/js/order/order-all-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js|/gsb/panda-extend/gsb.pubcontrol.js";
		listPartJsController = AllOrderListPart.class.getName();
		listPartServiceController = AllOrderListPart.class.getName();
		listFilter = "";
		// listPartName = formPartName = "全部订单";
		// meta = MtableManager.getMtable(entity);// 获取实体元数据
		// formPartName = listPartName = meta.getName();
		// listPartImportJs =
		// "/gsb/crm/base/js/customer-base-list.part.js|/gsb/platform/operation/crm/js/customer-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		// listFilter =
		// "id not in (SELECT customer_id from n_crm_customer_task GROUP BY customer_id)";
		// listPartJsController = NCustomerAllListPart.class.getName();
		// listPartServiceController = NCustomerAllListPart.class.getName();
	}

	public void createListToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("批量分配");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

//		PToolbarItem item = new PToolbarItem();
//		{
//			item.toNew();
//			item.setCode("detail");
//			item.setIcon(PToolbarHelper.iconExtr);
//			item.setName("订单详情");
//			item.setSeq(1);
//			item.setCommand("{controller}.detail();");
//			toolbar.getItems().add(item);
//		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("batchOrderTran");
			item.setIcon(PToolbarHelper.iconTran);
			item.setName("批量分配");
			item.setSeq(9);
			item.setCommand("{controller}.batchOrderTran(1);");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	@Test
	public void createRowToolbar() {
		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listrowToolbarPath);
			toolbar.setName("分配");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("orderTran");
			item.setName("分配");
			item.setSeq(2);
			item.setCommand("{controller}.orderTran();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid grid = super.createDatagrid(node);
		grid.setToolbar(listrowToolbarPath);
		return grid;
	}

}
