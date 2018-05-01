package com.gongsibao.trade.service.action.order.performance.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditPayService;
import com.gongsibao.bd.service.auditLog.AuditPayPerformanceService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.Pay;

/*创建回款业绩审核*/
public class ActionApplyPayPerformanceAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		@SuppressWarnings("unchecked")
		List<NDepPay> depPayList = (List<NDepPay>) ctx.getItem();
		AbstractAuditService auditLogHandler = AuditServiceFactory.create(AuditPayPerformanceService.class);
		List<AuditLog> auditLogList = auditLogHandler.execute(depPayList.get(0).getOrderId ());//插入的是订单id

		Map<String, Object> statusMap = new HashMap();
		statusMap.put ("audits", auditLogList);
		ctx.setStatus (statusMap);
	}
}
