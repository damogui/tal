package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProdUserMap;

public interface IOrderProdUserMapService extends IPersistableService<OrderProdUserMap> {
	
	int updateStatusByOrderProdId(List<Integer> orderProdIdList, Integer typeId, int newStatus, int oldStatus);
	
	//根据产品订单id获取该订单跟进人【关系类型序号，type=306，3061业务、3062客服（关注）、3063操作】
	Map<Integer, String> getOrderUserByIds(List<Integer>  pkidList,int typeId,int statusId);
}