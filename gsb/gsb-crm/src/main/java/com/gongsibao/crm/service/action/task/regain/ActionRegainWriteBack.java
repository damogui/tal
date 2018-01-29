package com.gongsibao.crm.service.action.task.regain;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw
 * 收回回写
 */
public class ActionRegainWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		Map<String,Object> getMap = ctx.getStatus();
		String  taskIds = getMap.get("taskIds").toString().replace("_", ",");
		//修改任务表中的业务员Id为空null，此时该任务进入公海
		String cmdText = "UPDATE n_crm_customer_task SET owner_id = NULL where id in(" + taskIds +")";
		pm.executeNonQuery(cmdText, null);		
	}

}
