package com.gongsibao.crm.service.action.customer.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;

import com.gongsibao.entity.crm.NCustomer;

/**
 * @author hw
 * 客户保存：校验
 */
public class ActionSaveCustomerVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomer entity = (NCustomer)ctx.getItem();
		
		EntityState state = entity.getEntityState();
		if (state == EntityState.Deleted) {

			throw new BusinessException("客户信息不允许删除！");
		}
		
	}

}
