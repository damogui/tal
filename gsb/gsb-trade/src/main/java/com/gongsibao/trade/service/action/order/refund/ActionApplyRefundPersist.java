package com.gongsibao.trade.service.action.order.refund;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.dic.RefundWayType;
import com.gongsibao.trade.base.IRefundService;

public class ActionApplyRefundPersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		IRefundService refundService = ServiceFactory.create(IRefundService.class);
		refund.toNew();
		refund.setWayType(RefundWayType.Dsh);
		//目前给默认值
		refund.setNo("");
		refund.setCost(new Integer(1));
		refundService.save(refund);
	}
}
