package com.gongsibao.trade.service.action.order.carryover;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderCarryStatusType;
import com.gongsibao.entity.trade.dic.OrderRefundStatusType;
import com.gongsibao.u8.base.ISoOrderService;

public class ActionApplyCarryoverWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
		
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder order = orderService.getByOrderId(carryOver.getFormOrderId());
		Integer carryAmount = order.getCarryAmount() == null ? 0 : order.getCarryAmount().intValue();
		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("is_carry_over", true);
			updateSql.set("carry_status_id", OrderCarryStatusType.Dsh.getValue());
			updateSql.set("carry_amount", carryAmount + carryOver.getAmount());
			updateSql.where("pkid =" + carryOver.getFormOrderId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		
	}

}
