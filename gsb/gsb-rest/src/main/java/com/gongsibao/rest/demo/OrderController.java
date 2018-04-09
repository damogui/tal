package com.gongsibao.rest.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	/*控制器要先执行次方法,进行初始化*/
	@RequestMapping(value = "/init",method = RequestMethod.GET)
	public void init() {
		
		Mtable meta = MtableManager.getMtable(Order.class);
		
		IDb db = DbFactory.create();
		db.reCreateTable(meta);
		
	}

	@RequestMapping(value = "/query_count",method = RequestMethod.GET)
	public Integer queryCount(@RequestParam("status") String status) {
		return 12;
	}

	@RequestMapping(value="/query_list",method = RequestMethod.GET)
	public List<Order> queryList(@RequestParam("filter") String filter){
		
		List<Order> orders = new ArrayList<Order>();
		
		for(int i=0;i<10;i++) {
			Order order = new Order();
			{
				order.setId(Integer.valueOf(i));
				order.setCode("001");
			}
			
			orders.add(order);
		}
		
		return orders;
	}

	@RequestMapping(value="/byid",method = RequestMethod.GET)
	public Order byId(@RequestParam("id") Integer id) throws InterruptedException {
		
		Order order = new Order();
		{
			order.setCode("001");
		}
		
		Thread.sleep(1000);
		
		return order;
	}

	@RequestMapping(value="/add",method = RequestMethod.PUT)
	public Order add(Order order) {
		
		order.setCode("DD"+order.getCode());
		
		return order;
	}
	
	@RequestMapping(value="/update",method = RequestMethod.PUT)
	public Order update( Order order) {
		
		order.setCode("DD"+order.getCode());
		order.setQuantity(order.getQuantity().add(BigDecimal.ONE));
		
		return order;
	}

	@RequestMapping(value="/delete",method = RequestMethod.DELETE)
	public Integer delete(@RequestParam("id") Integer id) {
		
		return id + 1 ;
	}
}
