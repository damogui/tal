package com.gongsibao.trade.service.action.order.save;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdItem;
import com.gongsibao.entity.trade.SoOrder;

/**
 * @ClassName: ActionSaveOrderPrice
 * @Description:TODO 重新计算价格，价格有变化，设置订单isChangePrice=true；
 * 执行顺序：5
 * @author: 韩伟
 * @date: 2018年3月2日 下午5:58:40
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveOrderPrice implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		SoOrder soOrder = (SoOrder) ctx.getItem();
		Integer totalPrice = 0;
		Integer payablePrice = 0;

		List<OrderProd> prodList = soOrder.getProducts();
		for (OrderProd prod : prodList) {

			Integer prodPrice = 0;
			Integer prodPriceOriginal = 0;
			List<OrderProdItem> itemList = prod.getItems();
			for (OrderProdItem item : itemList) {

				prodPrice += item.getPrice();
				prodPriceOriginal += item.getPriceOriginal();
			}
			prod.setPrice(prodPrice);
			prod.setPriceOriginal(prodPriceOriginal);

			totalPrice += prodPrice;
			payablePrice += prodPriceOriginal;
		}

		soOrder.setTotalPrice(totalPrice);

		// 原金额大于应付金额时,设置订单改价格状态为true
		if (totalPrice.compareTo(payablePrice) == 1) {

			soOrder.setIsChangePrice(true);
		}

		// 处理优惠劵优惠金额(放在最后处理)
		Integer disCountAmount = 0;
		List<OrderDiscount> discountList = soOrder.getDiscounts();
		if (discountList != null && discountList.size() > 0) {

			for (OrderDiscount item : discountList) {

				disCountAmount += item.getAmount();
			}
		}

		payablePrice = payablePrice - disCountAmount;
		soOrder.setPayablePrice(payablePrice);
		
		//钉钉共创阶段，给共创用户九折优惠（注：应该加入渠道概念处理）
	}
}
