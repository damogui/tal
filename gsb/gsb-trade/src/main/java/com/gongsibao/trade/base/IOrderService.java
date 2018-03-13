package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.SoOrder;

public interface IOrderService extends IPersistableService<SoOrder> {
	
	/**   
	 * @Title: applyStage   
	 * @Description: TODO(申请分期)
	 * @param: @param soOrder
	 * @param: @return
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean applyStage(SoOrder soOrder);
}