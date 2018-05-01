package com.gongsibao.trade.service.action.order.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditContractService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Contract;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		// 合同
		Contract contract = (Contract) ctx.getItem();
		Integer userId = SessionManager.getUserId();

		//		// 合同审核
		AbstractAuditService auditLogService = AuditServiceFactory.create(AuditContractService.class);
		List<AuditLog> auditLogList = auditLogService.execute(contract.getId());
		// 审核记录
		List<Integer> audiUserIdList = new ArrayList<>();
		for (AuditLog auditLog : auditLogList) {
			// 去掉自己
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
