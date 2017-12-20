package com.gongsibao.bd.service;

import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;

@Service
public class AuditLogService extends PersistableService<AuditLog> implements IAuditLogService {

	public AuditLogService() {
		super();
		this.type = AuditLog.class;
	}

	@Override
	public int updateStatus(Integer id, Integer status, Integer oldStatus, String remark) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("bd_audit_log");
			updateSql.set("status_id", status);
			updateSql.set("add_time", new Date());
			updateSql.set("remark", remark);
			updateSql.where("pkid=" + id + " AND status_id=" + oldStatus);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public int updateStatusByFidTidLev(Integer formId, Integer typeId, Integer level, Integer status, String levelLogic, Integer exceptId) {
		String exceptIdWhereString = exceptId.equals(0) ? " " : " AND pkid !=" + exceptId + " ";
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("bd_audit_log");
			updateSql.set("status_id", status);
			updateSql.where("form_id=" + formId + " AND type_id=" + typeId + " AND level " + levelLogic + " " + level + " " + exceptIdWhereString + "");
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}
}