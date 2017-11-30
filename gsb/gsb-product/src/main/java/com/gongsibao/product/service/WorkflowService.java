package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Workflow;
import com.gongsibao.product.base.IWorkflowService;

@Service
public class WorkflowService extends PersistableService<Workflow> implements IWorkflowService {

    public WorkflowService(){
        super();
        this.type=Workflow.class;
    }
}