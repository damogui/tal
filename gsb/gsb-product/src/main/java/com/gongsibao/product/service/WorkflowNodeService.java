package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.product.base.IWorkflowNodeService;

@Service
public class WorkflowNodeService extends PersistableService<WorkflowNode> implements IWorkflowNodeService {

    public WorkflowNodeService(){
        super();
        this.type=WorkflowNode.class;
    }
}