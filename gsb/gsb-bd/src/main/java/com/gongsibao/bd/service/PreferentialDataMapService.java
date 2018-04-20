package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IPreferentialDataMapService;
import com.gongsibao.entity.bd.PreferentialDataMap;
import org.netsharp.util.StringManager;

import java.util.*;

@Service
public class PreferentialDataMapService extends PersistableService<PreferentialDataMap> implements IPreferentialDataMapService {

    public PreferentialDataMapService(){
        super();
        this.type=PreferentialDataMap.class;
    }

    @Override
    public List<PreferentialDataMap> byPreferentialIds(Collection<Integer> preferentialIds) {
        if (null == preferentialIds || preferentialIds.isEmpty()) {
            return null;
        }
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("preferential_id IN (" + StringManager.join(",", Arrays.asList(preferentialIds.toArray())) + ") ");
        }
        return this.queryList(oql);
    }

    @Override
    public Map<Integer, List<PreferentialDataMap>> mapByPreferentialIds(Collection<Integer> preferentialIds) {
        Map<Integer, List<PreferentialDataMap>> result = new HashMap<>();
        List<PreferentialDataMap> list = byPreferentialIds(preferentialIds);
        if (null == list) {
            return result;
        }

        for (PreferentialDataMap dataMap : list) {
            Integer preferentialId = dataMap.getPreferentialId();
            List<PreferentialDataMap> dataMapList = result.get(preferentialId);
            if (null == dataMapList) {
                dataMapList = new ArrayList<>();
                result.put(preferentialId, dataMapList);
            }
            dataMapList.add(dataMap);
        }
        return result;
    }
}