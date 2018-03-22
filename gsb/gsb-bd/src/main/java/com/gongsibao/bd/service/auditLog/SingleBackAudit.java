package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

/**
 * 退单申请审核
 * @author Administrator
 *
 */
public class SingleBackAudit extends AbstractAuditLogService{

	@Override
	protected List<AuditLog> getExtenAuditLogList(Integer formId,
			Integer addUserId) {
		return null;
	}

	@Override
	protected AuditLogType setAuditLogType() {
		// TODO Auto-generated method stub
		return AuditLogType.Tdsq;
	}

}
