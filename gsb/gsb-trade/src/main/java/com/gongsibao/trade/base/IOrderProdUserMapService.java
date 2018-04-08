package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;

public interface IOrderProdUserMapService extends IPersistableService<OrderProdUserMap> {

	int updateStatusByOrderProdId(List<Integer> orderProdIdList, Integer typeId, int newStatus, int oldStatus);

	// 根据产品订单id获取该订单跟进人【关系类型序号，type=306，3061业务、3062客服（关注）、3063操作】
	Map<Integer, String> getOrderUserByIds(List<Integer> pkidList, int typeId, int statusId);

	// 根据订单id集合跟进人状态和跟进人类别获取最后的跟进人名称
	Map<Integer, String> getLastOperatorByOrderIdsStatusType(List<Integer> orderIdList, Integer typeId, Integer statusId);

	/**   
	 * @Title: updateStatus   
	 * @Description: TODO(更新状态)   
	 * @param: @param pkid
	 * @param: @param newStatus
	 * @param: @param oldStatus
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean updateStatus(Integer id, OrderProdUserMapStatus newStatus, OrderProdUserMapStatus oldStatus);
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(根据明细订单Id，人员类型查询)   
	 * @param: @param orderProdId
	 * @param: @param type
	 * @param: @return      
	 * @return: List<OrderProdUserMap>      
	 * @throws   
	 */
	List<OrderProdUserMap> queryList(Integer orderProdId,OrderProdUserMapType type);
}