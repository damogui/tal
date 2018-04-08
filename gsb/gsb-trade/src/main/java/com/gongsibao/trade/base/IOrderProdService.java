package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import com.gongsibao.entity.trade.dic.SettleStatus;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProd;

public interface IOrderProdService extends IPersistableService<OrderProd> {

	//根据订单id获取产品订单的产品名称
	Map<Integer, String> getProductCityNameByOrderIds(List<Integer> orderIdList);
	//根据订单id获取产品订单id集合
	List<Integer> getIdsByOrderIds(List<Integer> orderIdList);
	//更新是否分配 0 未分配 1已分配
	int updateAssignByIds(Integer isAssign, List<Integer>  pkidList);
	
	List<OrderProd> queryByOrderId(Integer orderId);

    boolean updateSettleStatus(List<Integer> orderProdIds, SettleStatus settleStatus);
    
    /**   
     * @Title: updateIsComplaint   
     * @Description: TODO(标记为投诉)   
     * @param: @param orderProdId
     * @param: @return      
     * @return: boolean      
     * @throws   
     */
    boolean updateIsComplaint(Integer orderProdId);
}