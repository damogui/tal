package com.gongsibao.trade.service.action.order.refund;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.dic.RefundWayType;
import com.gongsibao.trade.base.INDepRefundService;
import com.gongsibao.trade.base.IRefundService;

public class ActionApplyRefundPersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		//1.添加退款
		IRefundService refundService = ServiceFactory.create(IRefundService.class);
		refund.toNew();
		refund.setWayType(RefundWayType.Dsh);
		//目前给默认值
		refund.setNo("");
		refund.setCost(0);
		refundService.save(refund);
		//2.添加退款业绩
		INDepRefundService depRefundService = ServiceFactory.create(INDepRefundService.class);
		for (NDepRefund item : refund.getDepRefunds()) {
			NDepRefund entity = new NDepRefund();
			entity.toNew();
			entity.setOrderId(refund.getOrderId());
			entity.setAmount(item.getAmount());
			entity.setRefundId(item.getRefundId());
			entity.setSupplierId(item.getSupplierId());
			entity.setDepartmentId(item.getDepartmentId());
			entity.setEmployeeId(item.getEmployeeId());
			depRefundService.save(entity);
		}
	}
}
