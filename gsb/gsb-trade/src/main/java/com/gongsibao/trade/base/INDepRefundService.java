package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.NDepRefund;

import java.util.List;

/**
 * Created by win on 2018/2/27.
 */
public interface INDepRefundService extends IPersistableService<NDepRefund> {
    /*获取退款业绩根据退款Id*/
    List<NDepRefund> queryByRefundId(Integer refundId);
}
