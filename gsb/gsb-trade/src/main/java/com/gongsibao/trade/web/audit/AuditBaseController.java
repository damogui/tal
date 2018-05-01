package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

public abstract class AuditBaseController {

	IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);
	
	/**
	 * @Title: getAuditService
	 * @Description: TODO(获取审核服务)
	 * @param: @return
	 * @return: AbstractAuditLogService
	 * @throws
	 */
	protected abstract AbstractAuditService getAuditService();

	/**
	 * @Title: approved
	 * @Description: TODO(审核通过 )
	 * @param: @param auditLogId
	 * @param: @param remark
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean approved(Integer auditLogId, String remark) {

		return this.getAuditService().audit(AuditState.PASS, auditLogId, remark);
	}

	/**
	 * @Title: rejected
	 * @Description: TODO(驳回 注)
	 * @param: @param auditLogId
	 * @param: @param remark
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean rejected(Integer auditLogId, String remark) {

		return this.getAuditService().audit(AuditState.NOTPASS, auditLogId, remark);
	}

	/**
	 * @Title: getAuditLogList
	 * @Description: TODO(获取分期审核日志集合)
	 * @param: @param id
	 * @param: @param type
	 * @param: @return
	 * @return: List<AuditLog>
	 * @throws
	 */
	public List<AuditLog> getAuditLogList(Integer id, AuditLogType type) {

		Oql oql = new Oql();
		{
			oql.setType(AuditLog.class);
			oql.setSelects("auditLog.*,employee.{id,name}");
			oql.setFilter("form_id = ? and type_id = ?");
			oql.setOrderby("level");
			oql.getParameters().add("formId", id, Types.INTEGER);
			oql.getParameters().add("type", type.getValue(), Types.INTEGER);
		}
		List<AuditLog> logList = auditService.queryList(oql);
		return logList;
	}
}
