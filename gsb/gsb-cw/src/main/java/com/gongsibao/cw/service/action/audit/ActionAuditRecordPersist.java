package com.gongsibao.cw.service.action.audit;

import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.entity.cw.AuditRecord;

public class ActionAuditRecordPersist implements IAction {
	//审批记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	
    @Override
    public void execute(ActionContext ctx) {
    	AuditRecord auditRecord = (AuditRecord) ctx.getItem();
    	
    	UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("cw_audit_record");
			updateSql.set("status", auditRecord.getStatus().getValue());
			updateSql.set("memoto", auditRecord.getMemoto());
			updateSql.set("updator_id", auditRecord.getCreatorId());
			updateSql.set("update_time", new Date());
			updateSql.where("id =" + auditRecord.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<AuditRecord> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
    	ctx.setItem(auditRecord);
    }

}
