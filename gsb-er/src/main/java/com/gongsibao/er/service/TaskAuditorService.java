package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.TaskAuditor;
import com.gongsibao.er.base.ITaskAuditorService;

@Service
public class TaskAuditorService extends PersistableService<TaskAuditor> implements ITaskAuditorService {

    public TaskAuditorService(){
        super();
        this.type=TaskAuditor.class;
    }
}