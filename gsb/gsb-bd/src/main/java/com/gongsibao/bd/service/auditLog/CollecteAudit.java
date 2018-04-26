package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

/**
 * 收款申请审核
 * @author Administrator
 *
 */
public class CollecteAudit extends AbstractAuditLogService{

	@Override
	protected List<AuditLog> getExtenAuditLogList(Integer formId,
			Integer addUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuditLogType setAuditLogType() {
		// TODO Auto-generated method stub
		return AuditLogType.Sksq;
	}

	@Override
	protected String setActionPath() {
		return "gsb/crm/audit/pay";
	}

	@Override
	public List<String> getAuditPassTel() {
		return null;
	}

	@Override
	public List<String> getAuditFailTel() {
		return null;
	}

	@Override
	public List<String> getAuditWaitTel(int level) {
		return null;
	}
}
