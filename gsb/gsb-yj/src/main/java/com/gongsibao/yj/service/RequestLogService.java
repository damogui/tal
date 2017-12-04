package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.RequestLog;
import com.gongsibao.yj.base.IRequestLogService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class RequestLogService extends PersistableService<RequestLog> implements IRequestLogService {

    public RequestLogService(){
        super();
        this.type=RequestLog.class;
    }
}