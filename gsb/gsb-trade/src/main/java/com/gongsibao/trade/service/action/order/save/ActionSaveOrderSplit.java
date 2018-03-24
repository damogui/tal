package com.gongsibao.trade.service.action.order.save;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;

/**
 * @ClassName: ActionSaveOrderSplit
 * @Description:TODO 订单拆分：如果1条产品明细数量大于1则拆分成多个明细 执行顺序：2
 * @author: 韩伟
 * @date: 2018年3月2日 下午5:01:25
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveOrderSplit implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		SoOrder soOrder = (SoOrder) ctx.getItem();
		List<OrderProd> newProdList = new ArrayList<OrderProd>();
		List<OrderProd> oldProdList = soOrder.getProducts();

		for (OrderProd prod : oldProdList) {

			int quantity = prod.getQuantity();
			if (quantity > 1) {

				List<OrderProd> splitList = this.split(prod);
				newProdList.addAll(splitList);
			} else {
				newProdList.add(prod);
			}
		}

		this.assembleProdName(soOrder);

		soOrder.setProducts(newProdList);
	}

	/**
	 * @Title: assembleProdName
	 * @Description: TODO(构建产品名称（冗余，为了列表显示）)
	 * @param: @param soOrder
	 * @return: void
	 * @throws
	 */
	private void assembleProdName(SoOrder soOrder) {

		if (StringManager.isNullOrEmpty(soOrder.getProdName())) {

			List<String> ss = new ArrayList<String>();
			List<OrderProd> prodList = soOrder.getProducts();
			for (OrderProd orderProd : prodList) {

				String cityName = orderProd.getCityName();
				long count = prodList.stream().filter(x -> x.getProductId().equals(orderProd.getProductId()) && x.getCityId().equals(orderProd.getCityId())).count();
				String prodName = orderProd.getProductName() + "*" + count + "|" + cityName;
				if(!ss.contains(prodName)){
					
					ss.add(prodName);
				}
			}
			
			String fullProdName = StringManager.join("，", ss);
			soOrder.setProdName(fullProdName);
		}
	}

	/**
	 * @Title: split
	 * @Description: TODO(拆分)
	 * @param: @param prod
	 * @param: @return
	 * @return: List<OrderProd>
	 * @throws
	 */
	private List<OrderProd> split(OrderProd prod) {

		OrderProd newProd = null;
		List<OrderProd> splitList = new ArrayList<OrderProd>();
		int quantity = prod.getQuantity();
		for (int i = 0; i < quantity; i++) {

			newProd = new OrderProd();
			{
				newProd.toNew();
				newProd.setQuantity(1);
				newProd.setPrice(prod.getPrice());
				newProd.setPriceOriginal(prod.getPriceOriginal());
				newProd.setProductName(prod.getProductName());
				newProd.setProductId(prod.getProductId());
				newProd.setCityName(prod.getCityName());
				newProd.setCityId(prod.getCityId());
				newProd.setItems(prod.getItems());
			}

			splitList.add(newProd);
		}
		return splitList;
	}

	// 老系统代码:
	// List<Integer> orderProdIds = new ArrayList<>();
	// for (SoOrderProd soOrderProd : soOrder.getProdList()) {
	// soOrderProd.setOrderId(orderId);
	// soOrderProd.setIsAssign(0);
	//
	// log.info("----------------------- PROD_PRICE[" + orderId +
	// "] == 0 -------------------------");
	// log.info("----------------------- PROD_PRICE[" +
	// soOrderProd.getPriceOriginal() + "] == 0 -------------------------");
	// log.info("----------------------- PROD_PRICE[" + soOrder.getAddTime() +
	// "] == 0 -------------------------");
	//
	// if (soOrderProd.getPriceOriginal() == 0) {
	// log.info("----------------------- FUCK[" + soOrder.getPkid() +
	// "] == 0 -------------------------");
	// log.info("----------------------- FUCK[" + soOrderProd.getPriceOriginal()
	// + "] == 0 -------------------------");
	// }
	//
	// int quantity = soOrderProd.getQuantity();
	// for (int i = 0; i < quantity; i++) {
	// int orderProdId = soOrderProdDao.insert(soOrderProd);
	// orderProdIds.add(orderProdId);
	//
	// for (SoOrderProdItem soOrderProdItem : soOrderProd.getItemList()) {
	// soOrderProdItem.setOrderProdId(orderProdId);
	// soOrderProdItem.setQuantity(1);
	// soOrderProdItemDao.insert(soOrderProdItem);
	// }
	// }
	// }
}
