package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditInvoiceService;

public class AuditInvoiceController extends AuditBaseController{


	@Override
	protected AbstractAuditService getAuditService() {

		return AuditServiceFactory.create(AuditInvoiceService.class);
	}

}
