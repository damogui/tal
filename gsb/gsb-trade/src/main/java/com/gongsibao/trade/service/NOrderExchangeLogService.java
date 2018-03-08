package com.gongsibao.trade.service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.trade.base.INOrderExchangeLogService;
import com.gongsibao.entity.trade.NOrderExchangeLog;
import org.netsharp.communication.Service;

/**
 * Created by zhangchao on 2018/3/8.
 */
@Service
public class NOrderExchangeLogService extends SupplierPersistableService<NOrderExchangeLog> implements INOrderExchangeLogService {

    public NOrderExchangeLogService() {
        super();
        this.type = NOrderExchangeLog.class;
    }

}
