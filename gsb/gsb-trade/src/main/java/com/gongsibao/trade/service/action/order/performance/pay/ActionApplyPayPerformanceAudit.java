package com.gongsibao.trade.service.action.order.performance.pay;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.PayAudit;
import com.gongsibao.bd.service.auditLog.PayPerformanceAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.Pay;

/*创建回款业绩审核*/
public class ActionApplyPayPerformanceAudit implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		@SuppressWarnings("unchecked")
		List<NDepPay> depPayList = (List<NDepPay>) ctx.getItem();
		AbstractAuditLogService auditLogHandler = AuditFactory.getAudit(PayPerformanceAudit.class);
		List<AuditLog> auditLogList = auditLogHandler.execute(depPayList.get(0).getOrderId ());//插入的是订单id
		
		/****
		 * 
		 * 
		 * 
		 * 
		 */
	}
}
