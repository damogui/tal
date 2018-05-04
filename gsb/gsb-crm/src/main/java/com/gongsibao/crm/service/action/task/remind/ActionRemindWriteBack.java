package com.gongsibao.crm.service.action.task.remind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * 提醒：回写
 * @author Administrator
 *
 */
public class ActionRemindWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		//修改业务员的商机为待跟进（有下次跟进时间，并且是当天的）
		NCustomerTask task = (NCustomerTask) ctx.getItem();		
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			try {
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = format.format(new Date());
				updateSql.set("next_foolow_time",format.parse(dateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			updateSql.where("id =" + task.getId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		
	}

}
