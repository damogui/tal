package com.gongsibao.supplier.service.action.close;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.dict.SupplierStatus;

/**
 * @author hw
 * 校验是否已经销户
 */
public class ActionSupplierVerifyStatus  implements IAction{

	@Override
	public void execute(ActionContext ctx) {

		Supplier entity = (Supplier) ctx.getItem();
		if(entity.getStatus() == SupplierStatus.NOTOPEN){
			
			throw new BusinessException("未开通，不能销户！");
		}
		
		if(entity.getStatus() == SupplierStatus.CLOSED){
			
			throw new BusinessException("已销户，不能重复销户！");
		}
	}

}
