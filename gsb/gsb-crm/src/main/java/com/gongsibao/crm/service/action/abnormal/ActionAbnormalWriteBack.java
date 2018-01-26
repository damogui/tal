package com.gongsibao.crm.service.action.abnormal;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw
 * 抽查异常：回写
 */
public class ActionAbnormalWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem(); 
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("inspection_state", ctx.getStatus().get("type"));
			updateSql.set("last_inspection_user_id", SessionManager.getUserId());
			updateSql.set("last_inspection_content", getEntity.getLastInspectionContent());
			updateSql.set("last_inspection_time", df.format(day));
			updateSql.where("id=" + getEntity.getId());
		}
		String cmdText = updateSql.toSQL();
		pm.executeNonQuery(cmdText, null);
	}

}
