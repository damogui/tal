package com.gongsibao.crm.service.action.task.rollback;

import java.util.Map;

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
		Map<String, Object> setMap = ctx.getStatus();
		NCustomerTask taskEntity = (NCustomerTask)ctx.getItem();
		
		//退回级别：业务员（当前任务的ownerId等于当前登录人，退回到业务员当前的部门公海）、上级部门或平台（当前任务的ownerId所在部门的上级部门不为空退回上级部门公海，上级部门为空是平台公海）
		if(taskEntity.getOwnerId() !=null && taskEntity.getOwnerId().equals(SessionManager.getUserId())){
			setMap.put("ownerId", SessionManager.getUserId());
			taskEntity.setOwnerId(null);
		}else{
			Integer currentOwner = taskEntity.getOwnerId() != null ? taskEntity.getOwnerId() : SessionManager.getUserId();
			setMap.put("ownerId", currentOwner);
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(currentOwner);
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
