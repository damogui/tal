package com.gongsibao.crm.service.action.task.save;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;

/**
 * @ClassName: ActionSaveWriteBack
 * @Description:TODO 新增商机：回写客户taskCount
 * @author: 韩伟
 * @date: 2018年1月29日 下午4:10:02
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		EntityState state = ctx.getState();
		if (state == EntityState.New) {

			this.updateCustomerTaskCount(task.getCustomerId());
			this.updateCustomerTaskCount(task.getSourceId(),task.getCustomerId());
			
		}
	}

	/**
	 * 跟进客户的最近商机的来源Id
	 * @param customerId
	 */
	private void updateCustomerTaskCount(Integer lastCustomerSource, Integer customerId) {
		String tableName = MtableManager.getMtable(NCustomer.class).getTableName();
		String cmdText = String.format("update %s set last_customer_source= %s where pkid=%d", tableName,lastCustomerSource, customerId);
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
	
	private void updateCustomerTaskCount(Integer customerId) {

		String tableName = MtableManager.getMtable(NCustomer.class).getTableName();
		String cmdText = String.format("update %s set task_count=task_count+1 where pkid=%d", tableName, customerId);
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
}
