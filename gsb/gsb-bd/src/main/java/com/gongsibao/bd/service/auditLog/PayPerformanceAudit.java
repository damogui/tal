package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

public class PayPerformanceAudit extends AbstractAuditLogService {

	@Override
	protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuditLogType setAuditLogType() {

		return AuditLogType.Skyjsh;
	}

	@Override
	protected String setActionPath() {
		return "";
	}
}
