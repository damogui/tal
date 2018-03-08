package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NAudit;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INAuditService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NAuditService extends PersistableService<NAudit> implements INAuditService {

    public NAuditService(){
        super();
        this.type=NAudit.class;
    }
}
