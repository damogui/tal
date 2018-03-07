package com.gongsibao.u8.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;

public interface ISoOrderService extends IPersistableService<SoOrder> {

	Boolean updateManuaVoucherStatus(Integer orderId,OrderManualVoucherStatus status);
	//根据订单id集合获取订单客户名称
	Map<Integer,String> getCustNameByOrderIdList(List<Integer> orderIdList);

	//转移/分配（包括批量转移/分配）
	void orderTran(List<Integer> orderList, Integer toUserId);
}
