package com.gongsibao.crm.service.action.task.allocation.manual;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw
 * 分配校验
 */
public class ActionManualAllocationVerify implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomerTask taskEntity = (NCustomerTask)ctx.getItem();
		
		Integer supplierId =  taskEntity.getSupplierId();
		Integer departId =  taskEntity.getDepartmentId();
		Integer ownerId =  taskEntity.getOwnerId();
		//1.根据选择的服务商、部门是否为空，判断分配状态
		if(supplierId != null && ownerId == null && departId == null){
			taskEntity.setAllocationState(AllocationState.ALLOCATED_Supplier);
		}
		if(departId != null && ownerId == null){
			taskEntity.setAllocationState(AllocationState.ALLOCATED_Department);
		}
		//2.服务商和部门如果不选择，此时根据业务员Id,获取相应的服务商和部门
		if(ownerId != null){
			taskEntity.setAllocationState(AllocationState.ALLOCATED);
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(ownerId);
			
			if(departId == null){
				
				taskEntity.setDepartmentId(organization.getDepartmentId());
			}
			if(supplierId == null){
				
				taskEntity.setSupplierId(organization.getSupplierId());
			}
		}
		
	}
}