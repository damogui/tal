package com.gongsibao.tools.db;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.SingleBackAudit;

public class AuditTest {

	public static void main(String[] args) {
		AbstractAuditLogService service = AuditFactory.getAudit(SingleBackAudit.class);
		service.execute(2, 1806);
	}

}
