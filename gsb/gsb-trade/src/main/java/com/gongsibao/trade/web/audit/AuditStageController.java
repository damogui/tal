package com.gongsibao.trade.web.audit;

import java.sql.Types;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.StageAudit;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.SoOrder;

public class AuditStageController extends AuditBaseController{

	// 分期审核
	AbstractAuditLogService auditLogService = AuditFactory.getAudit(StageAudit.class);
	/**
	 * @Title: getSoOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: SoOrder
	 * @throws
	 */
	public SoOrder getSoOrder(Integer id) {
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,payablePrice,paidPrice,stageNum,stages.instalmentIndex,stages.percentage,stages.amount");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
	
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
}
