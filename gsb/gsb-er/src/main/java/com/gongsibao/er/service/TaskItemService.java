package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.TaskItem;
import com.gongsibao.er.base.ITaskItemService;

@Service
public class TaskItemService extends PersistableService<TaskItem> implements ITaskItemService {

    public TaskItemService(){
        super();
        this.type=TaskItem.class;
    }
}