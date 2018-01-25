package com.gongsibao.ncl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.ncl.NclBatch;
import com.gongsibao.ncl.base.INclBatchService;
import org.netsharp.communication.Service;

@Service
public class NclBatchService extends GsbPersistableService<NclBatch> implements INclBatchService {
    public NclBatchService() {
        super();
        this.type = NclBatch.class;
    }
}
