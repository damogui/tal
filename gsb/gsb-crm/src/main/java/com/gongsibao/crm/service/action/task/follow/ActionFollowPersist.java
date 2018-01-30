package com.gongsibao.crm.service.action.task.follow;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.service.NCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

/**
 * @author hw 跟进保存
 */
public class ActionFollowPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		// 补齐任务对应的客户Id
		Integer customerId = getCustomerId(foolow.getTaskId());
		foolow.setCustomerId(customerId);

		@SuppressWarnings("unchecked")
		IPersistableService<NCustomerTaskFoolow> service = (IPersistableService<NCustomerTaskFoolow>) ReflectManager.newInstance(NCustomerTaskFoolowService.class.getSuperclass());
		foolow = service.save(foolow);
		ctx.setItem(foolow);

	}

	private Integer getCustomerId(Integer taskId) {
		
		Oql oql = new Oql();
		{
			oql.setType(NCustomerTask.class);
			oql.setSelects("id,customerId");
			oql.setFilter("id=?");
			oql.getParameters().add("id", taskId, Types.INTEGER);
		}
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		NCustomerTask task = pm.queryFirst(oql);
		if (task == null) {

			throw new BusinessException("任务不存在或已删除，不能跟进！");
		}
		return task.getCustomerId();
	}

}
