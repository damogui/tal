package com.gongsibao.crm.service.action.task.allocation.manual;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;

/**
 * @author hw
 * 分配校验
 */
public class ActionManualAllocationVerify implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		//服务商和部门如果不选择，此时根据业务员Id,获取相应的服务商和部门
		NCustomerTask taskEntity = (NCustomerTask)ctx.getItem();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Salesman salesman = salesmanService.byEmployeeId(taskEntity.getOwnerId());	
		if(salesman !=null){
			if(taskEntity.getSupplierId() == null){
				taskEntity.setSupplierId(salesman.getSupplierId());
			}
			if(taskEntity.getDepartmentId() == null){
				taskEntity.setDepartmentId(salesman.getSupplierId());
			}
		}
	}

}
