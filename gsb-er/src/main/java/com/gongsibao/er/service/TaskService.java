package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Task;
import com.gongsibao.er.base.ITaskService;

@Service
public class TaskService extends PersistableService<Task> implements ITaskService {

    public TaskService(){
        super();
        this.type=Task.class;
    }
}