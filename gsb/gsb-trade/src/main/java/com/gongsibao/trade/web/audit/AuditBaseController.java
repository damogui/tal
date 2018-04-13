package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.INOrderCarryoverService;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.organization.entity.Employee;

public abstract class AuditBaseController {

	IAuditService auditService = ServiceFactory.create(IAuditService.class);
	ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
	INOrderCarryoverService carryoverService = ServiceFactory.create(INOrderCarryoverService.class);
	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public abstract Boolean approved(Integer auditLogId, String remark);

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public abstract Boolean rejected(Integer auditLogId, String remark);
	/**
	 * 获取分期审核日志集合
	 * @param id
	 * @return
	 */
	public List<AuditLog> getAuditLogList(Integer id,Integer auditLogType) {
		List<AuditLog> logList = new ArrayList<AuditLog>();
		Oql oql = new Oql();
		{
			oql.setType(AuditLog.class);
			oql.setSelects("auditLog.*,auditLog.employee.name");
			oql.setFilter("formId=? and type=?");
			oql.getParameters().add("formId", id, Types.INTEGER);
			oql.getParameters().add("type", auditLogType, Types.INTEGER);
		}
		logList = auditService.queryList(oql);
        for (AuditLog item:logList
             ) {
            if (item.getEmployee ()==null){
                Employee  em=new Employee ();
                em.setName ("");
                item.setEmployee (em);
            }

        }

		return logList;
	}
}
