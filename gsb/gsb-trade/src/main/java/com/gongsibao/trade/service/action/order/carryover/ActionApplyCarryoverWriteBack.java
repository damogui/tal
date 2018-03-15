package com.gongsibao.trade.service.action.order.carryover;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.u8.base.ISoOrderService;

public class ActionApplyCarryoverWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
		
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		SoOrder order = orderService.getByOrderId(carryOver.getFormOrderId());
		Integer carryAmount = order.getCarryAmount() == null ? 0 : order.getCarryAmount().intValue();
		//来源订单
		UpdateBuilder formUpdateSql = UpdateBuilder.getInstance();
		{
			formUpdateSql.update("so_order");
			formUpdateSql.set("is_carry_over", true);
			formUpdateSql.set("carry_status_id", AuditStatusType.Dsh.getValue());
			formUpdateSql.set("carry_amount", carryAmount + carryOver.getAmount());
			formUpdateSql.where("pkid =" + carryOver.getFormOrderId());
		}
		String formCmdText = formUpdateSql.toSQL();
		//去向订单
		UpdateBuilder toUpdateSql = UpdateBuilder.getInstance();
		{
			toUpdateSql.update("so_order");
			toUpdateSql.set("is_carry_over", true);
			toUpdateSql.set("carry_status_id", AuditStatusType.Dsh.getValue());
			toUpdateSql.where("pkid =" + carryOver.getToOrderId());
		}
		String toCmdText = toUpdateSql.toSQL();
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(formCmdText);
		cmdList.add(toCmdText);
		
		IPersister<SoOrder> pm = PersisterFactory.create();
		for (String item : cmdList) {
			pm.executeNonQuery(item, null);
		}
	}

}
