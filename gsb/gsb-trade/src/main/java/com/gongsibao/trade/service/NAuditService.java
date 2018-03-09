package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NAudit;
import com.gongsibao.trade.base.INAuditService;

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
