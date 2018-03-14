package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.json.EnumResultJson;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.u8.base.ISetOfBooksService;

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