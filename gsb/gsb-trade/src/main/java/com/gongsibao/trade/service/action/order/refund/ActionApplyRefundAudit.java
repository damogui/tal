package com.gongsibao.trade.service.action.order.refund;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditRefundService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Refund;

public class ActionApplyRefundAudit  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		// 退款审核
		AbstractAuditService auditLogService = AuditServiceFactory.create(AuditRefundService.class);
		//后期发送通知用
		List<AuditLog> auditLogList = auditLogService.execute(refund.getId());
		// 推送消息
		Map<String, Object> statusMap = new HashMap();
		statusMap.put ("audits", auditLogList);
		ctx.setStatus (statusMap);
	}

}
