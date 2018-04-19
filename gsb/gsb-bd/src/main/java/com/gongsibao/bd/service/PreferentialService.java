package com.gongsibao.bd.service;

import com.gongsibao.bd.base.IPreferentialService;
import com.gongsibao.entity.bd.Preferential;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import java.util.*;

@Service
public class PreferentialService extends PersistableService<Preferential> implements IPreferentialService {

    public PreferentialService() {
        super();
        this.type = Preferential.class;
    }

    @Override
    public List<Preferential> byIds(Collection<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            return null;
        }
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("id IN (" + StringManager.join(",", Arrays.asList(ids.toArray())) + ") ");
        }
        return this.queryList(oql);
    }

    @Override
    public Map<Integer, Preferential> mapByIds(Collection<Integer> ids) {
        Map<Integer, Preferential> result = new LinkedHashMap<>();
        if (null == ids || ids.isEmpty()) {
            return result;
        }
        List<Preferential> preferentials = byIds(ids);
        if (null == preferentials) {
            return result;
        }

        for (Preferential preferential : preferentials) {
            result.put(preferential.getId(), preferential);
        }
        return result;
    }
}