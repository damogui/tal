package com.gongsibao.trade.service.action.order.carryover;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.trade.base.INOrderCarryoverService;

public class ActionApplyCarryoverPersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
		INOrderCarryoverService overService = ServiceFactory.create(INOrderCarryoverService.class);
		carryOver.toNew();
		overService.save(carryOver);
	}
}
