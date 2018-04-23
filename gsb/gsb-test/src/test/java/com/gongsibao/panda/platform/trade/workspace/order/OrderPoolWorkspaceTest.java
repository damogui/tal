package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;

/**
 * Created by zhangchao on 2018/3/9.
 */
public class OrderPoolWorkspaceTest extends OrderALLWorkspaceTest {

	private String listrowToolbarPath = "/operation/roworderpool/toolbar";

	@Before
	public void setup() {

		super.setup();

		urlList = "/operation/order/pool/list";// 列表的url
		resourceNodeCode = "Operation_Order_Pool";
		listPartName = formPartName = "订单池";
		listToolbarPath = "operation/order/orderpool/edit";
		listFilter = "(owner_id is null or owner_id=0)";
		listPartServiceController = SalesmanAllOrderListPart.class.getName();
	}

	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid grid = super.createDatagrid(node);
		grid.setToolbar(listrowToolbarPath);
		return grid;
	}
	
	@Test
	@Override
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

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("batchOrderTran");
			item.setIcon(PToolbarHelper.iconTran);
			item.setName("批量订单分配");
			item.setSeq(9);
			item.setCommand("{controller}.batchOrderTran(1);");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	@Test
	@Override
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
}
