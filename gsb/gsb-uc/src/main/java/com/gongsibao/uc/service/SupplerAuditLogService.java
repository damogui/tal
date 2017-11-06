package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.SupplerAuditLog;
import com.gongsibao.uc.base.ISupplerAuditLogService;

@Service
public class SupplerAuditLogService extends PersistableService<SupplerAuditLog> implements ISupplerAuditLogService {

    public SupplerAuditLogService(){
        super();
        this.type=SupplerAuditLog.class;
    }
}