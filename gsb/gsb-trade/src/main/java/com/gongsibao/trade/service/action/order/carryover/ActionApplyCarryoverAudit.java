package com.gongsibao.trade.service.action.order.carryover;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.CarryoverAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NOrderCarryover;

public class ActionApplyCarryoverAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
		// 结转审核
		AbstractAuditLogService auditLogService = AuditFactory.getAudit(CarryoverAudit.class);
		//后期发送通知用
		List<AuditLog> auditLogList = auditLogService.execute(carryOver.getId());
	}

}
