package com.gongsibao.trade.service.action.order.stage;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;

public class ActionApplyStageWriteBack implements IAction{

	//分期金额
	private Integer getStageAllBigDecimal = 0;
	private int stageTimes = 0;
	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		for (NOrderStage item : order.getStages()) {
			getStageAllBigDecimal += item.getAmount().intValue();
			stageTimes += 1;
		}
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("staged", true);
			updateSql.set("paid_price", getStageAllBigDecimal);
			updateSql.set("stage_num", stageTimes);
			
			updateSql.where("pkid =" + order.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		
	}

}
