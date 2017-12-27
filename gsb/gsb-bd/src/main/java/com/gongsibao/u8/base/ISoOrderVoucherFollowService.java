package com.gongsibao.u8.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.SoOrderVoucherFollow;

public interface ISoOrderVoucherFollowService extends IPersistableService<SoOrderVoucherFollow> {

	Boolean addOrderVoucherFollowLog(int orderId,String content);
	
	List<SoOrderVoucherFollow> getOrderVoucherFollowLogByUserId(int orderId);
}
