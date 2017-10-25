package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;

@Service
public class AuditLogService extends PersistableService<AuditLog> implements IAuditLogService {

    public AuditLogService(){
        super();
        this.type=AuditLog.class;
    }
}