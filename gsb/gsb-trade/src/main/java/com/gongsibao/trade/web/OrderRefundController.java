package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.json.EnumResultJson;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.u8.base.ISetOfBooksService;
import com.gongsibao.u8.base.IU8BankService;

public class OrderRefundController {

	IOrderService orderService = ServiceFactory.create(IOrderService.class);

	public List<EnumResultJson> querySetOfBooksList() {

		Oql oql = new Oql();
		{
			oql.setType(SetOfBooks.class);
			oql.setSelects("id,name");
		}

		ISetOfBooksService setOfBooksService = ServiceFactory.create(ISetOfBooksService.class);
		List<SetOfBooks> list = setOfBooksService.queryList(oql);

		List<EnumResultJson> enumList = new ArrayList<EnumResultJson>();
		for (SetOfBooks sob : list) {

			EnumResultJson enumItem = new EnumResultJson();
			enumItem.setText(sob.getName());
			enumItem.setValue(sob.getId().toString());
			enumList.add(enumItem);
		}
		return enumList;
	}
	/**
	 * @param setOfBooksId
	 * @return
	 */
	public List<EnumResultJson> queryU8BankList(Integer setOfBooksId) {

		IU8BankService bankService = ServiceFactory.create(IU8BankService.class);
		Oql oql = new Oql();
		{
			oql.setType(U8Bank.class);
			oql.setSelects("id,name");
			oql.setFilter("setOfBooksId=? and enabled=1");
			oql.getParameters().add("setOfBooksId", setOfBooksId, Types.INTEGER);
		}
		List<U8Bank> list = bankService.queryList(oql);
		List<EnumResultJson> enumList = new ArrayList<EnumResultJson>();
		for (U8Bank bank : list) {

			EnumResultJson enumItem = new EnumResultJson();
			enumItem.setText(bank.getName());
			enumItem.setValue(bank.getId().toString());
			enumList.add(enumItem);
		}
		return enumList;
	}
	/**
	 * @Title: getSoOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: SoOrder
	 * @throws
	 */
	public SoOrder getSoOrder(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,no,payablePrice,paidPrice,refundPrice,carryAmount");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
	
	/**
	 * @Title: queryProductList
	 * @Description: TODO(查询产品明细)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderProd>
	 * @throws
	 */
	public List<OrderProd> queryProductList(Integer orderId) {

		IOrderProdService prodService = ServiceFactory.create(IOrderProdService.class);
		return prodService.queryByOrderId(orderId);
	}

	/**
	 * @Title: applyRefund
	 * @Description: TODO(申请退款)
	 * @param: @param soOrder
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean applyRefund(Refund refund) {

		return orderService.applyRefund(refund);
	}

}
