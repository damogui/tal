package com.gongsibao.trade.service.action.order.refund;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.u8.base.ISoOrderService;

/**
 * 回写订单的退款状态
 * @author Administrator
 *
 */
public class ActionApplyRefundWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Refund refund = (Refund) ctx.getItem();
		
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder order = orderService.getByOrderId(refund.getOrderId());
		Integer refundAmount = order.getRefundPrice() == null ? 0 : order.getRefundPrice().intValue();
		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("refund_status_id", AuditStatusType.Dsh.getValue());
			updateSql.set("refund_price", refundAmount + refund.getAmount());
			updateSql.where("pkid =" + refund.getOrderId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		
	}

}
