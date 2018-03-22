package com.gongsibao.trade.service.action.order.pay;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.PayAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Pay;

/*创建回款业绩审核*/
public class ActionApplyPayAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Pay pay = (Pay) ctx.getItem();
		AbstractAuditLogService auditLogHandler = AuditFactory.getAudit(PayAudit.class);
		List<AuditLog> auditLogList = auditLogHandler.execute(AuditLogType.Sksq, pay.getId(), null);

		// 推送消息
		
	}
}
