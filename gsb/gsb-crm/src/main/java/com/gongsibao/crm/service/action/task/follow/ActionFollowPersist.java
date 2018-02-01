package com.gongsibao.crm.service.action.task.follow;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.dic.QualityCategory;

/**
 * @author hw 跟进保存
 */
public class ActionFollowPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Integer getAmount = Integer.valueOf(ctx.getStatus().get("amount").toString());
		NCustomerTask task = (NCustomerTask) ctx.getItem();
		//supplierId，departmentId
		
		INCustomerTaskFoolowService foolowService = ServiceFactory.create(INCustomerTaskFoolowService.class);
		NCustomerTaskFoolow foolowTask = new NCustomerTaskFoolow();
		foolowTask.toNew();
		foolowTask.setCustomerId(task.getCustomerId());
		foolowTask.setTaskId(task.getId());
		foolowTask.setQualityCategory(QualityCategory.getItem(task.getIntentionCategory().getValue()));
		foolowTask.setQualityId(task.getQualityId());
		foolowTask.setNextFoolowTime(task.getNextFoolowTime());
		foolowTask.setContent(task.getLastContent());
		foolowTask.setEstimateAmount(getAmount);
		foolowTask.setSupplierId(task.getSupplierId());
		foolowTask.setDepartmentId(task.getDepartmentId());
		
		foolowService.save(foolowTask);
	}
}
