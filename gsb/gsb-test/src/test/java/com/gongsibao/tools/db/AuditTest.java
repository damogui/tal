package com.gongsibao.tools.db;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.ChangeOrderPriceAudit;
import com.gongsibao.entity.bd.dic.AuditLogType;

public class AuditTest {

	public static void main(String[] args) {
		AbstractAuditLogService service = AuditFactory.getAudit(ChangeOrderPriceAudit.class);
		service.execute(AuditLogType.Ddgj, 201480, 2178);
	}

}
