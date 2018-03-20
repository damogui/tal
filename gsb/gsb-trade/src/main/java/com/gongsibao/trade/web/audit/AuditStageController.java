package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Oql;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
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
	 * 获取分期审核日志集合
	 * @param id
	 * @return
	 */
	public List<AuditLog> getAuditLogList(Integer id) {
		List<AuditLog> logList = new ArrayList<AuditLog>();
		Oql oql = new Oql();
		{
			oql.setType(AuditLog.class);
			oql.setSelects("*");
			oql.setFilter("formId=? and type=?");
			oql.getParameters().add("formId", id, Types.INTEGER);
			oql.getParameters().add("type", AuditLogType.Fqsq.getValue(), Types.INTEGER);
		}
		logList = auditService.queryList(oql);
		return logList;
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
