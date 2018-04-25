package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.NOrderStage;

/**
 * Created by win on 2018/2/27.
 */
public interface INOrderStageService extends IPersistableService<NOrderStage> {
	
	/**
	 * 根据订单Id获取该订单分期信息
	 * @param orderId
	 * @return
	 */
	List<NOrderStage> getListByOrderId(Integer orderId);
	
}
