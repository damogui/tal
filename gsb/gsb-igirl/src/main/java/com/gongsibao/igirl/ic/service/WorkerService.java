package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Worker;
import com.gongsibao.igirl.ic.base.IcWorkerService;
import org.netsharp.communication.Service;

@Service
public class WorkerService extends GsbPersistableService<Worker> implements IcWorkerService {
    public WorkerService() {
        super();
        this.type = Worker.class;
    }
}
