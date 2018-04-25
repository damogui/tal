package com.gongsibao.supplier.action.supplier.open;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.dict.SupplierStatus;

/**
 * @author hw
 * 校验当前服务商的状态
 */
public class ActionSupplierVerifyStatus implements IAction{

	@Override
	public void execute(ActionContext ctx) {

		Supplier entity = (Supplier) ctx.getItem();
		
		//1.校验状态
		if(entity.getStatus() == SupplierStatus.OPEND){
			
			throw new BusinessException("已经开通，不能重复开通！");
		}
		
		//1.校验开能模块
		if (entity.getModules() == null || entity.getModules().size() == 0) {

			throw new BusinessException("没有设置功能模块，不能开户！");
		}
		
		String mobile = entity.getMobilePhone();
		
		//1.校验手机号是否为空
		if(StringManager.isNullOrEmpty(mobile)){
			
			throw new BusinessException("手机号不能为空！");
		}
		
	}
}
