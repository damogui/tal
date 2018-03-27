package com.gongsibao.trade.service.action.order.stage;

import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;

public class ActionApplyStageWriteBack implements IAction{

	//分期金额
	private String installmentMode = "";
	
	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		for (NOrderStage item : order.getStages()) {
			installmentMode += item.getAmount().intValue() + "|";
		}
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("is_installment", true);
			updateSql.set("stage_creator",SessionManager.getUserName());
			updateSql.set("stage_create_time",new Date());
			updateSql.set("installment_mode", installmentMode.substring(0,installmentMode.length()-1));
			updateSql.set("stage_num", order.getStages().size());
			
			updateSql.where("pkid =" + order.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		
	}

}
