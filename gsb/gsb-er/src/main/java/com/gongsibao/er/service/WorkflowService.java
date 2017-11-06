package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Workflow;
import com.gongsibao.er.base.IWorkflowService;

@Service
public class WorkflowService extends PersistableService<Workflow> implements IWorkflowService {

    public WorkflowService(){
        super();
        this.type=Workflow.class;
    }
}