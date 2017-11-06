package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IOperateLogService;
import com.gongsibao.entity.bd.OperateLog;

@Service
public class OperateLogService extends PersistableService<OperateLog> implements IOperateLogService {

    public OperateLogService(){
        super();
        this.type=OperateLog.class;
    }
}