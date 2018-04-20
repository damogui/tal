package com.gongsibao.rest.web.demo;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class SalesOrderController {

	/* 控制器要先执行次方法,进行初始化 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public void init() {

		Mtable meta = MtableManager.getMtable(SalesOrder.class);

		IDb db = DbFactory.create();
		db.reCreateTable(meta);

	}

	@RequestMapping(value = "/query_count", method = RequestMethod.GET)
	public Integer queryCount(@RequestParam("status") String status) {

		ISalesOrderService orderService = ServiceFactory.create(ISalesOrderService.class);

		Oql oql = new Oql();
		{
			oql.setSelects("id");
		}

		int count = orderService.queryCount(oql);

		return count;
	}

	@RequestMapping(value = "/query_list", method = RequestMethod.GET)
	public List<SalesOrder> queryList(@RequestParam("filter") String filter) {

		ISalesOrderService orderService = ServiceFactory.create(ISalesOrderService.class);

		Oql oql = new Oql();
		{
			oql.setSelects("SalesOrder.*");
		}

		List<SalesOrder> orders = orderService.queryList(oql);

		return orders;
	}

	@RequestMapping(value = "/byid", method = RequestMethod.GET)
	public SalesOrder byId(@RequestParam("id") Integer id) throws InterruptedException {
		
		ISalesOrderService orderService = ServiceFactory.create(ISalesOrderService.class);

		SalesOrder order = orderService.byId(id);

		return order;
	}

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public SalesOrder add(SalesOrder order) {

		ISalesOrderService orderService = ServiceFactory.create(ISalesOrderService.class);
		order.toNew();

		order = orderService.save(order);

		return order;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public SalesOrder update(SalesOrder order) {

		ISalesOrderService orderService = ServiceFactory.create(ISalesOrderService.class);

		order = orderService.save(order);

		return order;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Boolean delete(@RequestParam("id") Integer id) {

		ISalesOrderService orderService = ServiceFactory.create(ISalesOrderService.class);

		SalesOrder order = orderService.byId(id);
		order.toDeleted();
		orderService.save(order);

		return true;
	}
}
