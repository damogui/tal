package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.ContractAudit;

public class AuditContractController extends AuditBaseController{

	// 合同审核
	AbstractAuditLogService auditLogService = AuditFactory.getAudit(ContractAudit.class);

	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId) {
		return auditLogService.audit(AuditState.PASS,auditLogId,null);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {
		return auditLogService.audit(AuditState.NOTPASS,auditLogId,remark);
	}

}
