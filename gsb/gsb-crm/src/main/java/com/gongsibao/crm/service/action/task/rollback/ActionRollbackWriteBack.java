package com.gongsibao.crm.service.action.task.rollback;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @author hw
 * 回退：回写
 */
public class ActionRollbackWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem();
		//修改任务表中的业务员Id为空null，此时该任务进入公海
		String cmdText = "UPDATE n_crm_customer_task SET owner_id = NULL where id=" + getEntity.getId();
		pm.executeNonQuery(cmdText, null);		
	}

}
