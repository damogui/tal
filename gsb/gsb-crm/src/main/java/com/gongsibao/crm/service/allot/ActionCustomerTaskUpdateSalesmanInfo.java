package com.gongsibao.crm.service.allot;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;

/**
 * @author zhangchao 业务员配置信息的回写
 */
public class ActionCustomerTaskUpdateSalesmanInfo implements IAction {

	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask entity = (NCustomerTask) ctx.getItem();
		// 查出最新的任务信息
		entity = nCustomerTaskService.byId(entity.getId());
		if (entity == null) {
			throw new BusinessException("该任务不存在");
		}
		
		//(根据employeeId获取)   
		Salesman salesman = salesmanService.byEmployeeId(entity.getOwnerId());
		
		

		// 更新【跟进中任务数量】
		salesman.setFollowUpTaskCount((salesman.getFollowUpTaskCount() == null ? 0 : salesman.getFollowUpTaskCount()) + 1);
		// 更新当天分配的任务数量】
		salesman.setFollowUpTaskDayCount(0);
		// 更新【当天分配的任务数量】
		salesman.setFollowUpTaskWeekCount(0);

		salesmanService.save(salesman);
	}

}
