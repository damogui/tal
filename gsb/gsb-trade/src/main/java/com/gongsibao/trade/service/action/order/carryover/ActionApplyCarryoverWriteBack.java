package com.gongsibao.trade.service.action.order.carryover;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
/**
 * 回写订单的结转状态(其他的回写信息在审核通过后回写)
 * @author Administrator
 *
 */
public class ActionApplyCarryoverWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
		
		//来源订单
		UpdateBuilder formUpdateSql = UpdateBuilder.getInstance();
		{
			formUpdateSql.update("so_order");
			formUpdateSql.set("carry_status_id", AuditStatusType.Shz.getValue());
			formUpdateSql.where("pkid =" + carryOver.getFormOrderId());
		}
		String formCmdText = formUpdateSql.toSQL();
		//去向订单
		UpdateBuilder toUpdateSql = UpdateBuilder.getInstance();
		{
			toUpdateSql.update("so_order");
			toUpdateSql.set("carry_status_id", AuditStatusType.Shz.getValue());
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
