package com.gongsibao.trade.web.audit;

import com.gongsibao.trade.service.action.audit.AuditState;

public class AuditContractController extends AuditBaseController{

	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId) {

		return auditService.auditContract(AuditState.PASS, auditLogId, null);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {

		return auditService.auditContract(AuditState.NOTPASS, auditLogId, remark);
	}

}
