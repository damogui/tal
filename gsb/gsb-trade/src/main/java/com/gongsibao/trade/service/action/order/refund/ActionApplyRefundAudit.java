package com.gongsibao.trade.service.action.order.refund;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.RefundAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Refund;

public class ActionApplyRefundAudit  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		// 退款审核
		AbstractAuditLogService auditLogService = AuditFactory.getAudit(RefundAudit.class);
		//后期发送通知用
		List<AuditLog> auditLogList = auditLogService.execute(refund.getId());
		
	}

}
