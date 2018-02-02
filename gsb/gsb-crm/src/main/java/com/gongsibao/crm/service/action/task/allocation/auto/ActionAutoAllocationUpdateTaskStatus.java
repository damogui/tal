package com.gongsibao.crm.service.action.task.allocation.auto;

import java.sql.Types;
import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.utils.DateUtils;

/**
 * @author zhangchao 任务的分配状态的回写
 */
public class ActionAutoAllocationUpdateTaskStatus implements IAction {

	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask entity = (NCustomerTask) ctx.getItem();

		// 验证非空
		if (entity == null) {
			throw new BusinessException("该任务不存在！");
		}

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("allocation_state", AllocationState.WAIT.getValue());
			updateSql.where("id=?");
		}

		// 状态改为【待分配】
		entity.setAllocationState(AllocationState.WAIT);
		// 最后分配人Id（机器分配，默认写0）
		entity.setLastAllocationUserId(0);
		if (!entity.getOwnerId().equals(0) && entity.getAllocationType().equals(NAllocationType.AUTO)) {
			// 状态改为【已分配】
			updateSql.set("allocation_state", AllocationState.ALLOCATED.getValue());
			// 跟进状态改为【待跟进】
			updateSql.set("foolow_status", CustomerFollowStatus.UNFollow.getValue());
			// 跟新最后分配时间
			updateSql.set("last_allocation_time", DateUtils.getDateStr(new Date()));
		}
		String cmdText = updateSql.toSQL();
		QueryParameters qps = new QueryParameters();
		qps.add("id", entity.getId(), Types.INTEGER);
		IPersister<NCustomerTask> taskPm = PersisterFactory.create();
		taskPm.executeNonQuery(cmdText, qps);
	}
}
