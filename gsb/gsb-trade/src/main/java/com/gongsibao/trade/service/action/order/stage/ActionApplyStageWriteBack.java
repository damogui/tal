package com.gongsibao.trade.service.action.order.stage;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
/**
 * 回写订单的分期状态(其他的回写信息在审核通过后回写)
 * @author Administrator
 *
 */
public class ActionApplyStageWriteBack implements IAction{
	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("installment_audit_status_id", AuditStatusType.Shz.getValue());
			updateSql.set("stage_create_time", order.getStageCreateTime());
			updateSql.set("installment_mode", order.getInstallmentMode());			
			updateSql.set("stage_creator",SessionManager.getUserName());
			updateSql.set("stage_num", order.getStages().size());
			updateSql.where("pkid =" + order.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		
	}

}
