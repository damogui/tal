package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.InvoiceAudit;

public class AuditInvoiceController extends AuditBaseController{

	// 发票审核
	AbstractAuditLogService auditLogService = AuditFactory.getAudit(InvoiceAudit.class);
	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId) {
		return auditLogService.audit(AuditState.PASS, auditLogId, null);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {
		return auditLogService.audit(AuditState.NOTPASS, auditLogId, remark);
	}

}
