package com.gongsibao.bd.service.auditLog;

public class AuditServiceFactory {
	
	public static AbstractAuditService create(Class<?> c){
		
		AbstractAuditService audit = null;
        try {
        	
        	audit = (AbstractAuditService) Class.forName(c.getName()).newInstance();
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        return audit;
    }
}
