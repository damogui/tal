package com.gongsibao.trade.service.action.order.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.InvoiceAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Invoice;

public class ActionApplyInvoiceAudit  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Invoice invoice = (Invoice) ctx.getItem();
		Integer userId = SessionManager.getUserId();

		//发票审核
		AbstractAuditLogService auditLogService = AuditFactory.getAudit(InvoiceAudit.class);

		List<AuditLog> auditLogList = auditLogService.execute(invoice.getId());
		//审核记录
		List<Integer> audiUserIdList = new ArrayList<>();
		for (AuditLog auditLog : auditLogList) {
			//去掉自己
			if (!auditLog.getCreatorId().equals(userId)) {
				audiUserIdList.add(auditLog.getCreatorId());
			}
		}
		// 推送消息
		Map<String, Object> statusMap = new HashMap();
		statusMap.put ("audits", auditLogList);
		ctx.setStatus (statusMap);
	}
}
