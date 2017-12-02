package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.QueryLog;
import com.gongsibao.yj.base.IQueryLogService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class QueryLogService extends PersistableService<QueryLog> implements IQueryLogService {

    public QueryLogService(){
        super();
        this.type=QueryLog.class;
    }
}