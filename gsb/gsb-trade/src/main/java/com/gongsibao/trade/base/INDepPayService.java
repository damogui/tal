package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.NDepPay;

/**
 * Created by win on 2018/2/27.
 */
/*回款业绩*/
public interface INDepPayService extends IPersistableService<NDepPay> {
	
	Boolean applyPayPerformance(List<NDepPay> depPayList);
}
