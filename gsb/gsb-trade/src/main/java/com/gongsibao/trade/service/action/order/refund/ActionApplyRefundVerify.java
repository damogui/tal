package com.gongsibao.trade.service.action.order.refund;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.u8.base.ISoOrderService;

public class ActionApplyRefundVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder order = orderService.getByOrderId(refund.getOrderId());
		Integer instaAmount = order.getPaidPrice().intValue();
		//1.验证退款金额是否超出
		if(refund.getAmount() > instaAmount){
			throw new BusinessException("退款金额不应大于应付的金额！");
		}
		//2.验证退款类型
		switch (refund.getRefundType().getValue()) {
		case 0:
			if(refund.getAmount().equals(instaAmount)){
				throw new BusinessException("部分退款类型时，退款金额应小于应付的金额！");
			}
			break;
		case 1:
			if(!refund.getAmount().equals(instaAmount)){
				throw new BusinessException("全额退款类型时，退款金额应等于应付的金额！");
			}
			break;
		}
	}

}
