package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.WorkflowFile;
import com.gongsibao.product.base.IWorkflowFileService;

@Service
public class WorkflowFileService extends PersistableService<WorkflowFile> implements IWorkflowFileService {

    public WorkflowFileService(){
        super();
        this.type=WorkflowFile.class;
    }
}