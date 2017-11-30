package com.gongsibao.ma.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.ma.MaOrder;

public interface IMaOrderService  extends IPersistableService<MaOrder>{

	/**   
	 * @Title: leaderPass   
	 * @Description: TODO(提交审核)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	@Transaction
	boolean submitAudit(Integer orderId);
	
	/**   
	 * @Title: leaderPass   
	 * @Description: TODO(组长审核通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	@Transaction
	boolean leaderPass(Integer orderId);

	/**   
	 * @Title: leaderNoPass   
	 * @Description: TODO(组长审核不通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	@Transaction
	boolean leaderNoPass(Integer orderId);
	
	/**   
	 * @Title: vpPass   
	 * @Description: TODO(VP审核通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */	
	@Transaction
	boolean vpPass(Integer orderId);
	
	/**   
	 * @Title: vpNoPass   
	 * @Description: TODO(VP审核不通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	@Transaction
	boolean vpNoPass(Integer orderId);
}
