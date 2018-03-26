package com.gongsibao.trade.web.audit;

import java.sql.Types;

import com.gongsibao.bd.service.auditLog.*;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.NOrderCarryover;

public class AuditCarryoverController extends AuditBaseController {

	// 结转审核
	AbstractAuditLogService auditLogService = AuditFactory.getAudit(CarryoverAudit.class);
	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId) {
		return auditLogService.audit(AuditState.PASS, auditLogId, null);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {
		return auditLogService.audit(AuditState.NOTPASS, auditLogId, remark);
	}
	
	/**
	 * @Title: getNOrderCarryover
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: NOrderCarryover
	 * @throws
	 */
	public NOrderCarryover getNOrderCarryover(Integer id) {
		Oql oql = new Oql();
		{
			oql.setType(NOrderCarryover.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		NOrderCarryover entity = carryoverService.queryFirst(oql);
		return entity;
	}
}
