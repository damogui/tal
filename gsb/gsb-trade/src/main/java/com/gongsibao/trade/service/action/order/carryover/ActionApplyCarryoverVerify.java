package com.gongsibao.trade.service.action.order.carryover;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.u8.base.ISoOrderService;

public class ActionApplyCarryoverVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
		
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder order = orderService.getByOrderId(carryOver.getFormOrderId());
		//已支付金额
		Integer paidAmount = order.getPaidPrice().intValue();
		//已退金额
		Integer refundPrice = order.getRefundPrice() == null ? 0 : order.getRefundPrice().intValue();
		//结转金额
		Integer carryAmount = order.getCarryAmount() == null ? 0 : order.getCarryAmount().intValue();
		Integer carryAllAmount = carryAmount.intValue() + carryOver.getAmount().intValue();
		
		Integer allAmount = carryAllAmount.intValue() + refundPrice.intValue(); 
		//1.验证结转总金额是否超出
		if(allAmount > paidAmount){
			throw new BusinessException("结转金额不应大于应付的金额！");
		}
		//2.去向订单号是否存在
		SoOrder toOrder = orderService.getByOrderNo(carryOver.getToOrderNo());
		if(toOrder == null){
			throw new BusinessException("去向订单号输入有误！");
		}
	}
}
