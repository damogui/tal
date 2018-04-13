package com.gongsibao.trade.service.action.order.settle;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.ContractAudit;
import com.gongsibao.bd.service.auditLog.SettleAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.settle.Settle;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 结算审核
 */
public class ActionApplySettleAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		// 合同
		Settle settle = (Settle) ctx.getItem();
		Integer userId = SessionManager.getUserId();
		Map<String, Object> status = ctx.getStatus();
		// 合同审核
		AbstractAuditLogService auditLogService = AuditFactory.getAudit(SettleAudit.class);
		List<AuditLog> auditLogList = auditLogService.execute(settle.getId());
		// 审核记录
		List<Integer> auditUserIdList = new ArrayList<>();
		for (AuditLog auditLog : auditLogList) {
			// 去掉自己
			if (!auditLog.getCreatorId().equals(userId)) {
				auditUserIdList.add(auditLog.getCreatorId());
			}
		}
		// 需要发通知的人员id
		status.put("auditUserIdList", auditUserIdList);
	}
}
