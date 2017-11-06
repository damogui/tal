package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.PriceAudit;
import com.gongsibao.product.base.IPriceAuditService;

@Service
public class PriceAuditService extends PersistableService<PriceAudit> implements IPriceAuditService {

    public PriceAuditService(){
        super();
        this.type=PriceAudit.class;
    }
}