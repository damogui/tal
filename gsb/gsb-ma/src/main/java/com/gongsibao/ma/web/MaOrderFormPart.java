package com.gongsibao.ma.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.ma.base.IMaOrderService;

public class MaOrderFormPart extends FormPart{

	IMaOrderService orderService = ServiceFactory.create(IMaOrderService.class);
	
	/**   
	 * @Title: leaderPass   
	 * @Description: TODO(提交审核)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean submitAudit(Integer orderId){
		
		return orderService.submitAudit(orderId);
	}
	
	/**   
	 * @Title: leaderPass   
	 * @Description: TODO(组长审核通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean leaderPass(Integer orderId){
		
		//组长审核状态变为【审核通过】
		//VP审核状态变为【待审核】
		
		return orderService.leaderPass(orderId);
	}
	
	/**   
	 * @Title: leaderNoPass   
	 * @Description: TODO(组长审核不通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean leaderNoPass(Integer orderId){
		//组长审核状态变为【审核不通过】
		
		return orderService.leaderNoPass(orderId);
	}
	
	/**   
	 * @Title: vpPass   
	 * @Description: TODO(VP审核通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean vpPass(Integer orderId){
		//VP审核状态变为【审核通过】
		
		return orderService.vpPass(orderId);
	}
	
	/**   
	 * @Title: vpNoPass   
	 * @Description: TODO(VP审核不通过)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean vpNoPass(Integer orderId){
		
		//VP审核状态变为【审核不通过】
		
		return  orderService.vpNoPass(orderId);
	}
}
