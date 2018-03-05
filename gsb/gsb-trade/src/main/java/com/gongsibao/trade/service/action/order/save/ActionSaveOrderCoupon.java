package com.gongsibao.trade.service.action.order.save;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.entity.trade.SoOrder;

/**
 * @ClassName: ActionSaveOrderCoupon
 * @Description:TODO 处理优惠劵
 * 执行顺序：7
 * @author: 韩伟
 * @date: 2018年3月2日 下午5:06:23
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveOrderCoupon implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		// 只更新优惠劵的情况？
		// 价格不一致时，是否要生成一条优惠信息？
		// 优惠信息在前端生成
		SoOrder soOrder = (SoOrder) ctx.getItem();
		List<OrderDiscount> discountList = soOrder.getDiscounts();
		if (discountList != null && discountList.size() > 0) {

			for (OrderDiscount item : discountList) {

				Integer preferentialId = item.getPreferentialId();
				// 更新优惠劵的使用状态
				String cmdText = "UPDATE bd_preferential_code SET status = ?,use_time = ?,order_id = ? WHERE preferential_id = ? AND no = ?";
				QueryParameters qps = new QueryParameters();
				{
					qps.add("@status", 2, Types.INTEGER);
					qps.add("@use_time", new Date(), Types.DATE);
					qps.add("@order_id", soOrder.getId(), Types.INTEGER);
					qps.add("@preferential_id", preferentialId, Types.INTEGER);
					qps.add("@no", item.getNo(), Types.VARCHAR);
				}
				IPersister<SoOrder> pm = PersisterFactory.create();
				pm.executeNonQuery(cmdText, qps);
			}

		}
	}
}

// 写入优惠券
// if (soOrder.getSoOrderDiscountList() != null) {
// for (SoOrderDiscount item : soOrder.getSoOrderDiscountList()) {
// item.setOrderId(orderId);
// item.setAddUserId(currentUserId);
// soOrderDiscountService.insert(item);
//
// //更新优惠券使用状态
// Map<String, Object> params = new HashMap<>();
// params.put("preferential_id", item.getPreferentialId());
// params.put("no", item.getNo());
// params.put("use_time", item.getAddTime());
// params.put("order_id", orderId);
// bdPreferentialCodeDao.updatePreferentialStatus(params);
// //soOrderDiscountService.updateDiscount(item.getSqlId());
// }
// }