package com.gongsibao.trade.web.audit;

import java.sql.Types;

import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.audit.AuditState;

public class AuditStageController extends AuditBaseController{

	
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

		return auditService.auditStage(AuditState.PASS, auditLogId, null);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {

		return auditService.auditStage(AuditState.NOTPASS, auditLogId, remark);
	}
}
