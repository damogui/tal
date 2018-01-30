package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.igirl.base.IGirlConfigService;
import com.gongsibao.igirl.base.INclBatchService;
import com.gongsibao.igirl.utils.JsonFormatTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import java.io.File;
import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
                oql.getParameters().add("currentStatus",true, Types.BOOLEAN);
            }
            NclBatch nb = this.queryFirst(oql);
            if (nb!=null){
                nb.setCurrentStatus(false);
                super.save(nb);
            }
        }
        return super.save(entity);
    }
}
