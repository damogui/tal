package com.gongsibao.trade.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.service.action.audit.AuditContext;
import com.gongsibao.trade.service.action.audit.AuditState;

@Service
public class AuditService extends PersistableService<SoOrder> implements IAuditService {

	public AuditService() {
		super();
		this.type = SoOrder.class;
	}

	@Override
	public Boolean auditCarryover(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/carryover");
	}

	@Override
	public Boolean auditContract(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/contract");
	}

	@Override
	public Boolean auditCost(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/cost");
	}

	@Override
	public Boolean auditInvoice(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/invoice");
	}

	@Override
	public Boolean auditOrder(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/order");
	}

	@Override
	public Boolean auditPay(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/pay");
	}

	@Override
	public Boolean auditPerformance(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/performance");
	}

	@Override
	public Boolean auditRefund(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/refund");
	}

	@Override
	public Boolean auditStage(AuditState state, Integer auditLogId, String remark) {

		return this.audit(state, auditLogId, remark, "gsb/crm/audit/stage");
	}

	private Boolean audit(AuditState state, Integer auditLogId, String remark, String actionPath) {

		AuditContext auditContext = new AuditContext();
		{
			// 这里根据传入的参数构造;
			auditContext.setState(state);
			auditContext.setAuditLogId(auditLogId);
			auditContext.setremark(remark);
		}
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath(actionPath);
			ctx.setItem(auditContext);
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}
}