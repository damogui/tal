package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.ncl.NclBatch;
import com.gongsibao.igirl.base.INclBatchService;
import jdk.internal.org.objectweb.asm.Type;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

@Service
public class NclBatchService extends GsbPersistableService<NclBatch> implements INclBatchService {
    public NclBatchService() {
        super();
        this.type = NclBatch.class;
    }

    @Override
    public NclBatch save(NclBatch entity) {
        if (entity.isCurrentStatus()){
            Oql oql=new Oql();{
                oql.setType(NclBatch.class);
                oql.setSelects("NclBatch.*");
                oql.setFilter("currentStatus=?");
                oql.getParameters().add("currentStatus",true, Type.BOOLEAN);
            }
            NclBatch nb = this.queryFirst(oql);
            nb.setCurrentStatus(false);
            super.save(nb);
        }
        return super.save(entity);
    }
}
