package com.gongsibao.cw.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.entity.cw.AuditRecord;


@Service
public class AuditRecordService extends PersistableService<AuditRecord> implements IAuditRecordService{

	public AuditRecordService() {
		super();
		this.type = AuditRecord.class;
	}

	@Override
	public Boolean saveAudit(AuditRecord auditRecord) {
		
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/audit/form");
            ctx.setItem(auditRecord);
            ctx.setState(auditRecord.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}
}
