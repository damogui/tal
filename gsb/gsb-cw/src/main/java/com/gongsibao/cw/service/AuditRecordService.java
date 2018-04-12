package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.dict.FinanceDict;


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
	
	public List<AuditRecord> getAuditRecordList(Integer formId,Integer formType){
		//审核记录  
		Oql oql = new Oql();
		oql.setType(AuditRecord.class);
		oql.setSelects("auditRecord.*,auditRecord.employee.name");
		oql.setFilter("formId=? and formType= ?");
		oql.getParameters().add("formId", formId, Types.INTEGER);
		oql.getParameters().add("formType", formType, Types.INTEGER);
		return this.queryList(oql);
	}

	@Override
	public Boolean deleteAuditByFormId(Integer formId, Integer formType) {
		String sql = "delete from cw_audit_record where form_id = '"+formId+"' and form_type = '"+formType+"'";
		return this.pm.executeNonQuery(sql, null) > 0;
	}

	@Override
	public void saveFinance(AuditRecord auditRecord) {
		 AuditRecord au = new AuditRecord();
	   	 au.toNew();
	   	 au.setAuditUserId(SessionManager.getUserId());  
	   	 au.setFormType(auditRecord.getFormType());
	   	 au.setFormId(auditRecord.getFormId());
	   	 au.setApplyUserId(auditRecord.getApplyUserId());
	   	 au.setApplyDepartmentId(auditRecord.getApplyDepartmentId());
	   	 au.setMemoto(auditRecord.getMemoto());
	   	 au.setUpdateTime(new Date());
	   	 au.setStatus(FinanceDict.AuditDetailStatus.DONE); //财务办理完成
	   	 this.save(au);
	}
}
