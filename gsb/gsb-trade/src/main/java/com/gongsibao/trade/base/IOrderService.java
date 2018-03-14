package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
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
	@Transaction
	Boolean applyStage(SoOrder soOrder);
	
	
	/**   
	 * @Title: applyRefund   
	 * @Description: TODO(申请退款)   
	 * @param: @param refund
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	Boolean applyRefund(Refund refund);
	
	/**   
	 * @Title: applyCarryover   
	 * @Description: TODO(申请结转)   
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	Boolean applyCarryover(NOrderCarryover orderCarryover);
}