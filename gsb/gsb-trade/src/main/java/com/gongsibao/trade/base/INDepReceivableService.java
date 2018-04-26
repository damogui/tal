package com.gongsibao.trade.base;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.NDepReceivable;
import org.netsharp.core.annotations.Transaction;

import java.util.List;

/**
 * Created by win on 2018/2/27.
 */
public interface INDepReceivableService extends IPersistableService<NDepReceivable> {
    @Transaction
    void updateStatus(Integer id, AuditStatusType bhsh);

    /*根据订单id获取实体信息*/
    List<NDepReceivable> getNDepsByOrderId(Integer id);
}
