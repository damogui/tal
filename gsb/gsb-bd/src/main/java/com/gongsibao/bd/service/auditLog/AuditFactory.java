package com.gongsibao.bd.service.auditLog;

public class AuditFactory {
	public static AbstractAuditLogService getAudit(Class<?> c){
		AbstractAuditLogService audit = null;
        try {
        	audit = (AbstractAuditLogService) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audit;
    }
}
