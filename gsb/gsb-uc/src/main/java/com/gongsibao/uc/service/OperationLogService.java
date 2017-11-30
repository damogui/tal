package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.OperationLog;
import com.gongsibao.uc.base.IOperationLogService;

@Service
public class OperationLogService extends PersistableService<OperationLog> implements IOperationLogService {

    public OperationLogService(){
        super();
        this.type=OperationLog.class;
    }
}