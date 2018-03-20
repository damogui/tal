package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;

public class ContractFormPart extends FormPart{

	public IPersistable newInstance(Object par) {

		this.getService();

		IPersistable entity = this.service.newInstance();
		Contract contract = (Contract)entity;
		
		SoOrder soOrder = getSoOrder(par);
		if(soOrder != null){
			contract.setSoOrder(soOrder);
			List<OrderProd> products= getOrderProdList(par);
			contract.setProducts(products);
		}
		
		return contract;
	}
	
	private SoOrder getSoOrder(Object id) {

		StringBuilder builder = new StringBuilder();
		builder.append("SoOrder.*,");
		builder.append("SoOrder.department.*,");
		builder.append("SoOrder.owner.*");
		
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects(builder.toString());
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
	
	private List<OrderProd>  getOrderProdList(Object id) {

		StringBuilder builder = new StringBuilder();
		builder.append("OrderProd.*");
		builder.append("OrderProd.items.*");
		Oql oql = new Oql();
		{
			oql.setType(OrderProd.class);
			oql.setSelects(builder.toString());
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", id, Types.INTEGER);
		}

		IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
		List<OrderProd> list = orderProdService.queryList(oql);
		return list;
	}
}
