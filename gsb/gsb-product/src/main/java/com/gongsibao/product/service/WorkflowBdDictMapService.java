package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.WorkflowBdDictMap;
import com.gongsibao.product.base.IWorkflowBdDictMapService;

@Service
public class WorkflowBdDictMapService extends PersistableService<WorkflowBdDictMap> implements IWorkflowBdDictMapService {

    public WorkflowBdDictMapService(){
        super();
        this.type=WorkflowBdDictMap.class;
    }
}