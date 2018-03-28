package com.gongsibao.trade.service.action.order.save;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;

/**
 * @ClassName: ActionSaveOrderVerify
 * @Description:TODO 订单校验 执行顺序：1
 * @author: 韩伟
 * @date: 2018年3月2日 下午5:00:32
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveOrderVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		SoOrder soOrder = (SoOrder) ctx.getItem();
		if (soOrder.getProducts() == null || soOrder.getProducts().size() == 0) {

			throw new BusinessException("没有选择产品服务项");
		}

		if (soOrder.getPlatformSource() == null) {

			throw new BusinessException("没有访问来源未知");
		}
		
		if(soOrder.getAccountId() == null || soOrder.getAccountId().compareTo(0) == 0){
			
			throw new BusinessException("会员帐号不存在");
		}

		List<OrderDiscount> discountList = soOrder.getDiscounts();
		for (OrderDiscount discount : discountList) {

			Boolean isValidCoupon = hasValidCoupon(discount.getPreferentialId());
			if (!isValidCoupon) {

				throw new BusinessException("所选择的优惠券不可用");
			}
		}

		List<OrderProd> prodList = soOrder.getProducts();
		for (OrderProd prod : prodList) {

			if (prod.getCityId() == null || prod.getCityId().compareTo(0) == 0) {

				throw new BusinessException("地区信息不能为空");
			}

			if (prod.getProductId() == null || prod.getProductId().compareTo(0) == 0) {

				throw new BusinessException("没有添加产品");
			}

			// 判断产品是否存，是否有效
			Boolean isValidProd = hasValidProd(prod.getProductId());
			if (!isValidProd) {

				throw new BusinessException("产品不存在或已下架");
			}

			if (prod.getItems() == null || prod.getItems().size() == 0) {

				throw new BusinessException("没有添加产品服务项");
			}
		}
	}

	/**
	 * @Title: isValidProd
	 * @Description: TODO(根据产品Id判断是否有效)
	 * @param: @param prodId
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	private Boolean hasValidProd(Integer prodId) {

		//1.是否存在
		//2.是否上架
		
		return true;
	}

	/**
	 * @Title: hasValidCoupon
	 * @Description: TODO(判断优惠劵是否有有效)
	 * @param: @param couponId
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	private Boolean hasValidCoupon(Integer couponId) {

		//1.状态必须是已激活
		//2.在有效期内
		//3.是否有效
		return true;
	}
}
