package com.gongsibao.trade.service.action.order.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditPayService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Pay;

/*创建回款审核*/
public class ActionApplyPayAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Pay pay = (Pay) ctx.getItem();
        AbstractAuditService auditLogHandler = AuditServiceFactory.create(AuditPayService.class);
		List<AuditLog> auditLogList = auditLogHandler.execute(pay.getId());

		// 推送消息
		Map<String, Object> statusMap = new HashMap();
		statusMap.put ("audits", auditLogList);
		ctx.setStatus (statusMap);
	}
}
