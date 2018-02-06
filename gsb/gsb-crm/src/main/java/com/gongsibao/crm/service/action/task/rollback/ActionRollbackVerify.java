package com.gongsibao.crm.service.action.task.rollback;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw
 * 回退：校验
 */
public class ActionRollbackVerify implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask taskEntity = (NCustomerTask)ctx.getItem();
		//退回级别：业务员（当前任务的ownerId等于当前登录人，否则为部门级别）、部门（获取当前登录人的上级部门Id，回写数据）
		if(taskEntity.getOwnerId().equals(SessionManager.getUserId())){
			taskEntity.setOwnerId(null);
		}else{
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(taskEntity.getOwnerId());
			ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
			Integer currentDepartmentSupId = departmentService.getSupDepartmentId(organization.getDepartmentId());
			//当前部门的上级为空，进入平台公海。否则进入上级部门公海
			if(currentDepartmentSupId == null){
				taskEntity.setSupplierId(null);
				taskEntity.setDepartmentId(null);
				taskEntity.setOwnerId(null);
			}else {
				taskEntity.setDepartmentId(currentDepartmentSupId);
				taskEntity.setOwnerId(null);
			}
		}
	}
	
}
