package com.gongsibao.ma.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.dic.DemandSoldOutState;
import com.gongsibao.entity.ma.dic.SelingStatus;

/**   
 * @ClassName:  ISellingDemandService   
 * @Description:TODO 
 * @author: 韩伟
 * @date:   2017年9月25日 下午7:05:13   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public interface ISellingDemandService extends IPersistableService<SellingDemand> {
	
	/**   
	 * @Title: getMatchingList   
	 * @Description: TODO(获取可匹配的销售需求) 交易状态为：未出售,已预定
	 * @param: @return      
	 * @return: List<SellingDemand>      
	 * @throws   
	 */
	List<SellingDemand> getMatchingList();
	
	/**   
	 * @Title: updateSelingStatus   
	 * @Description: TODO(更新出售状态)   
	 * @param: @param id
	 * @param: @param selingStatus
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	boolean updateSelingStatus(Integer id,SelingStatus selingStatus);
	
	/**   
	 * @Title: changeSoldOutState   
	 * @Description: TODO(更新上下架状态)   
	 * @param: @param id
	 * @param: @param soldOutState
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	boolean changeSoldOutState(int id,DemandSoldOutState soldOutState);
}