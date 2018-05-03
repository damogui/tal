package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.NOrderExchangeLog;

/**
 * Created by zhangchao on 2018/3/8.
 */
public interface INOrderExchangeLogService extends IPersistableService<NOrderExchangeLog> {

	List<NOrderExchangeLog> queryByOrderId(Integer orderId);
}
