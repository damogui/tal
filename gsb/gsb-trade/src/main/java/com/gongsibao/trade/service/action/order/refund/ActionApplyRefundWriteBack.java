package com.gongsibao.trade.service.action.order.refund;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;

/**
 * 回写订单的退款状态(其他的回写信息在审核通过后回写)
 * @author Administrator
 *
 */
public class ActionApplyRefundWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		Refund refund = (Refund) ctx.getItem();
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			//订单审核状态为审核中，待审核通过或者不通过再次回写订单一些信息
			updateSql.set("refund_status_id", AuditStatusType.Shz.getValue());
			updateSql.where("pkid =" + refund.getOrderId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}

}
