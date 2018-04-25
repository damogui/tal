package com.gongsibao.trade.service.action.order.refund;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.ISoOrderService;

public class ActionApplyRefundVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder order = orderService.getByOrderId(refund.getOrderId());
		//已支付金额
		Integer paidAmount = order.getPaidPrice().intValue();
		//已退金额
		Integer refundPrice = order.getRefundPrice() == null ? 0 : order.getRefundPrice().intValue();
		
		//1.验证可退款金额(可退款金额=已付金额-已退金额  yyk提供公式)总金额是否超出
		if(refundPrice > paidAmount){
			throw new BusinessException("该订单无可退款金额，请知悉");
		}
		//其他验证待完善
	}
}
