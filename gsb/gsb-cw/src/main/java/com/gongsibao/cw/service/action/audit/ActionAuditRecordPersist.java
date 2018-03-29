package com.gongsibao.cw.service.action.audit;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.entity.cw.AuditRecord;

public class ActionAuditRecordPersist implements IAction {
	//审批记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	
    @Override
    public void execute(ActionContext ctx) {
    	AuditRecord auditRecord = (AuditRecord) ctx.getItem();
    	auditRecord.toPersist();
    	auditRecordService.save(auditRecord);
    	ctx.setItem(auditRecord);
    }

}
