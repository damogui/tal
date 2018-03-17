package com.gongsibao.trade.web.audit;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.trade.base.IAuditService;

public abstract class AuditBaseController {

	IAuditService auditService = ServiceFactory.create(IAuditService.class);

	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public abstract Boolean approved(Integer auditLogId);

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public abstract Boolean rejected(Integer auditLogId, String remark);
}
